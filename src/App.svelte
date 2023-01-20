<script>
  import TimeLabel from "./components/TimeLabel.svelte";
  import SettingsButton from "./components/SettingsButton.svelte";
  import UpdateButton from "./components/UpdateButton.svelte";
  import StartStopButton from "./components/StartStopButton.svelte";
  import TimerTabs from "./components/TimerTabs.svelte";
  import { configStore, defaultConfig } from "./stores/ConfigStore";
  import { timerRunning } from "./stores/TimerStore";
  import { onMount } from "svelte";

  onMount(async () => {
    const c = await api.getConfig();
    configStore.set(Object.assign({}, defaultConfig, c || {}));
    configStore.subscribe((c) => api.postConfig(c));
  });

  let disabled = false;
  $: disabled = $timerRunning;
</script>

{#if $configStore}
  <main class="d-flex h-100 w-100 min-h-0">
    <div class="flex-fill d-flex flex-column justify-content-between me-1 w-100 min-h-0">
      <TimeLabel />
      <div>
        <SettingsButton {disabled} />
      </div>
    </div>
    <div class="flex-fill d-flex flex-column ms-1 w-100">
      <TimerTabs {disabled} />
      <div class="d-flex justify-content-between">
        <UpdateButton {disabled} />
        <StartStopButton />
      </div>
    </div>
  </main>
{/if}

<style>
</style>
