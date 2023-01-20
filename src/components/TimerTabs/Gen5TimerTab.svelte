<script>
  import { configStore } from "../../stores/ConfigStore";
  import { gen5TimerMode } from "../../constants/Gen5TimerMode";

  export let disabled;
</script>

<div class="h-100 d-flex flex-column min-h-0">
  <div class="row mb-1">
    <label class="col-sm-3 col-form-label" for="gen5TimerMode">Mode</label>
    <div class="col-sm-9">
      <select id="gen5TimerMode" class="form-select form-select-sm" {disabled} bind:value={$configStore.gen5.timerMode}>
        {#each Object.keys(gen5TimerMode) as m}
          <option value={m}>{m}</option>
        {/each}
      </select>
    </div>
  </div>
  <div class="border border-3 border-white bg-white bg-opacity-50 rounded-1 flex-fill mb-2 p-2 overflow-y-auto overflow-x-hidden">
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen5Calibration">Calibration</label>
      <div class="col-sm-6">
        <input id="gen5Calibration" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.calibration} />
      </div>
    </div>
    {#if gen5TimerMode.DEFAULT != $configStore.gen5.timerMode}
      <div class="row mb-1">
        <label class="col-sm-6 col-form-label" for="gen5TargetDelay">Target Delay</label>
        <div class="col-sm-6">
          <input id="gen5TargetDelay" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.targetDelay} />
        </div>
      </div>
    {/if}
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen5TargetSecond">Target Second</label>
      <div class="col-sm-6">
        <input id="gen5TargetSecond" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.targetSecond} />
      </div>
    </div>
    {#if ![gen5TimerMode.DEFAULT, gen5TimerMode.C_GEAR].includes($configStore.gen5.timerMode)}
      <div class="row mb-1">
        <label class="col-sm-6 col-form-label" for="gen5EntralinkCalibration">Entralink Calibr.</label>
        <div class="col-sm-6">
          <input id="gen5EntralinkCalibration" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.entralinkCalibration} />
        </div>
      </div>
    {/if}
    {#if ![gen5TimerMode.DEFAULT, gen5TimerMode.C_GEAR, gen5TimerMode.ENTRALINK].includes($configStore.gen5.timerMode)}
      <div class="row mb-1">
        <label class="col-sm-6 col-form-label" for="gen5FrameCalibration">Frame Calibr.</label>
        <div class="col-sm-6">
          <input id="gen5FrameCalibration" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.frameCalibration} />
        </div>
      </div>
      <div class="row mb-1">
        <label class="col-sm-6 col-form-label" for="gen5TargetAdvances">Target Advances</label>
        <div class="col-sm-6">
          <input id="gen5TargetAdvances" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.targetAdvances} />
        </div>
      </div>
    {/if}
  </div>
  {#if gen5TimerMode.C_GEAR != $configStore.gen5.timerMode}
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen5SecondHit">Second Hit</label>
      <div class="col-sm-6">
        <input id="gen5SecondHit" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.secondHit} />
      </div>
    </div>
  {/if}
  {#if gen5TimerMode.DEFAULT != $configStore.gen5.timerMode}
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen5DelayHit">Delay Hit</label>
      <div class="col-sm-6">
        <input id="gen5DelayHit" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.delayHit} />
      </div>
    </div>
  {/if}
  {#if gen5TimerMode.ENHANCED_ENTRALINK == $configStore.gen5.timerMode}
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen5ActualAdvances">Actual Advances</label>
      <div class="col-sm-6">
        <input id="gen5ActualAdvances" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen5.actualAdvances} />
      </div>
    </div>
  {/if}
</div>

<style></style>
