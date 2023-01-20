<script>
  import { timerRemaining, timerRunning, timerStages, timerCurrentStage, timerStoreReset } from "../stores/TimerStore";
  import { configStore } from "../stores/ConfigStore";
  import { getSound } from "../constants/Sound";
  import { timerMode } from "../constants/TimerMode";
  import { task, sleep } from "../util/Utils";
  import customTimerService from "../service/CustomTimerService";
  import gen3TimerService from "../service/Gen3TimerService";
  import gen4TimerService from "../service/Gen4TimerService";
  import gen5TimerService from "../service/Gen5TimerService";
  import flowTimerTimerService from "../service/FlowTimerTimerService";

  let sound;
  let actions = [];
  let stages = [];

  $: sound = getSound($configStore.actionSettings.sound);
  $: actions = createActions($configStore.actionSettings.count, $configStore.actionSettings.interval);
  $: stages = createStages($configStore);

  const createActions = (count, interval) => {
    const array = [];
    for (let i = 0; i <= count; i++) {
      array.push(i * interval + (i == count ? 1000 : 0));
    }
    return array;
  };

  const createStages = (config) => {
    switch (config.currentTimerMode) {
      case timerMode.GEN3:
        return gen3TimerService.getStages(config);
      case timerMode.GEN4:
        return gen4TimerService.getStages(config);
      case timerMode.GEN5:
        return gen5TimerService.getStages(config);
      case timerMode.CUSTOM:
        return customTimerService.getStages(config);
      case timerMode.FLOW_TIMER:
        return flowTimerTimerService.getStages(config);
      default:
        return [];
    }
  };

  const startStop = () => {
    if ($timerRunning) {
      $timerRunning = false;
    } else {
      run();
    }
  };

  const run = () => {
    const interval = $configStore.timerSettings.refreshInterval;
    $timerRunning = true;
    $timerStages = stages;

    task(async () => {
      for (let i = 0; i < $timerStages.length; i++) {
        const a = [...actions];
        const stage = $timerStages[i];
        let remaining = stage;

        $timerCurrentStage = i + 1;

        let loaded = false;
        const start = Date.now();
        while ($timerRunning && remaining >= 0) {
          await sleep(interval);
          remaining = stage != Number.MAX_VALUE ? stage - (Date.now() - start) : Date.now() - start;
          if (remaining <= a[a.length - 1]) {
            a.pop();
            if (loaded) {
              sound.play();
            } else {
              loaded = true;
              sound.load();
            }
          }

          $timerRemaining = remaining;
        }
      }
      task(() => {
        timerStoreReset();
      }, 10);
    });
  };
</script>

<button type="button" class="btn btn-primary btn-sm flex-fill ms-1 link-white" on:click={startStop}>
  {#if $timerRunning}
    Stop
  {:else}
    Start
  {/if}
</button>

<style></style>
