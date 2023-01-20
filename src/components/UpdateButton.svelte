<script>
  import { configStore } from "../stores/ConfigStore";
  import { timerMode } from "../constants/TimerMode";
  import gen3TimerService from "../service/Gen3TimerService";
  import gen4TimerService from "../service/Gen4TimerService";
  import gen5TimerService from "../service/Gen5TimerService";

  export let disabled;

  const update = () => {
    switch ($configStore.currentTimerMode) {
      case timerMode.GEN3:
        $configStore.gen3.calibration = gen3TimerService.calibrate($configStore);
        $configStore.gen3.frameHit = null;
        break;
      case timerMode.GEN4:
        $configStore.gen4.calibratedDelay = gen4TimerService.calibrate($configStore);
        $configStore.gen4.delayHit = null;
        break;
      case timerMode.GEN5:
        const calibrationResult = gen5TimerService.calibrate($configStore);
        $configStore.gen5.calibration = calibrationResult.calibration;
        $configStore.gen5.entralinkCalibration = calibrationResult.entralinkCalibration;
        $configStore.gen5.frameCalibration = calibrationResult.frameCalibration;
        $configStore.gen5.secondHit = null;
        $configStore.gen5.delayHit = null;
        $configStore.gen5.actualAdvances = null;
        break;
      case timerMode.FLOW_TIMER:
      case timerMode.CUSTOM:
      default:
        break;
    }
  };
</script>

<button type="button" {disabled} class="btn btn-light btn-sm flex-fill me-1 link-dark" on:click={update}>Update</button>

<style></style>
