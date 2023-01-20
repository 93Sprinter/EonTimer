<script>
  import { configStore } from "../../stores/ConfigStore";
  import { gen4TimerMode } from "../../constants/Gen4TimerMode";

  export let disabled;
</script>

<div class="h-100 d-flex flex-column">
  <div class="row mb-1">
    <label class="col-sm-3 col-form-label" for="gen4TimerMode">Mode</label>
    <div class="col-sm-9">
      <select id="gen4TimerMode" class="form-select form-select-sm" {disabled} bind:value={$configStore.gen4.timerMode}>
        {#each Object.keys(gen4TimerMode) as m}
          <option value={m}>{m}</option>
        {/each}
      </select>
    </div>
  </div>
  <div class="border border-3 border-white bg-white bg-opacity-50 rounded-1 flex-fill mb-2 p-2">
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen4CalibratedDelay">Calibrated Delay</label>
      <div class="col-sm-6">
        <input id="gen4CalibratedDelay" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen4.calibratedDelay} />
      </div>
    </div>
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen4CalibratedSecond">Calibrated Second</label>
      <div class="col-sm-6">
        <input id="gen4CalibratedSecond" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen4.calibratedSecond} />
      </div>
    </div>
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen4TargetDelay">Target Delay</label>
      <div class="col-sm-6">
        <input id="gen4TargetDelay" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen4.targetDelay} />
      </div>
    </div>
    <div class="row mb-1">
      <label class="col-sm-6 col-form-label" for="gen4TargetSecond">Target Second</label>
      <div class="col-sm-6">
        <input id="gen4TargetSecond" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen4.targetSecond} />
      </div>
    </div>
  </div>
  <div class="row">
    <label class="col-sm-6 col-form-label" for="gen4DelayHit">Delay Hit</label>
    <div class="col-sm-6">
      <input id="gen4DelayHit" class="form-control form-control-sm" type="number" min="0" {disabled} bind:value={$configStore.gen4.delayHit} />
    </div>
  </div>
</div>

<style></style>
