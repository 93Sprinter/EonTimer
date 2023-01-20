<script>
  import { configStore } from "../../stores/ConfigStore";

  export let disabled;
</script>

<div class="h-100 d-flex flex-column">
  <div class="row">
    <label class="col-sm-3 col-form-label" for="flowTimerValue">Value</label>
    <div class="col-sm-9">
      <input id="flowTimerValue" class="form-control form-control-sm" type="text" {disabled} bind:value={$configStore.flowTimer.value} />
    </div>
  </div>
</div>

<style></style>
