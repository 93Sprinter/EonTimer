<script>
  import { gen3TimerMode } from "../../constants/Gen3TimerMode";
  import { configStore } from "../../stores/ConfigStore";
  import { timerPrimed, timerStages } from "../../stores/TimerStore";
  import calibrationUtil from "../../service/util/CalibrationUtil";

  export let disabled;

  const setVariableTarget = () => {
    const targetFrame = calibrationUtil.toMillis($configStore.gen3.targetFrame || 0, $configStore.timerSettings.handheld);
    const calibration = $configStore.gen3.calibration || 0;
    $timerStages[1] = targetFrame + calibration;
    $timerPrimed = true;
  };
</script>

<div class="h-100 d-flex flex-column">
  <div class="row mb-1">
    <label class="col-sm-3 col-form-label" for="gen3TimerMode">Mode</label>
    <div class="col-sm-9">
      <select id="gen3TimerMode" class="form-select form-select-sm" {disabled} bind:value={$configStore.gen3.timerMode}>
        {#each Object.keys(gen3TimerMode) as m}
          <option value={m}>{m}</option>
        {/each}
      </select>
    </div>
  </div>
  <div class="border border-3 border-white bg-white bg-opacity-50 rounded-1 flex-fill mb-2 p-2">
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen3Calibration">Calibration</label>
      <div class="col-sm-6">
        <input id="gen3Calibration" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen3.calibration} />
      </div>
    </div>
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen3PreTimer">Pre-Timer</label>
      <div class="col-sm-6">
        <input id="gen3PreTimer" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen3.preTimer} />
      </div>
    </div>
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen3TargetFrame">Target Frame</label>
      <div class="col-sm-6">
        {#if gen3TimerMode.VARIABLE == $configStore.gen3.timerMode}
          <input id="gen3TargetFrame" class="form-control form-control-sm" type="number" min="0" disabled={!disabled || $timerPrimed} bind:value={$configStore.gen3.targetFrame} />
        {:else}
          <input id="gen3TargetFrame" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen3.targetFrame} />
        {/if}
      </div>
    </div>
    {#if gen3TimerMode.VARIABLE == $configStore.gen3.timerMode}
      <button type="button" disabled={!disabled || $timerPrimed} on:click={setVariableTarget} class="btn btn-primary btn-sm flex-fill me-1 link-white float-end">Set Target Frame</button>
    {/if}
  </div>
  <div class="row">
    <label class="col-sm-6 col-form-label" for="gen3FrameHit">Frame Hit</label>
    <div class="col-sm-6">
      <input id="gen3FrameHit" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen3.frameHit} />
    </div>
  </div>
</div>

<style></style>
