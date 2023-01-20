import { gen5TimerMode } from "../constants/Gen5TimerMode";
import secondTimerFactory from "./factory/SecondTimerFactory";
import delayTimerFactory from "./factory/DelayTimerFactory";
import entralinkTimerFactory from "./factory/EntralinkTimerFactory";
import enhancedEntralinkTimerFactory from "./factory/EnhancedEntralinkTimerFactory";
import calibrationUtil from "./util/CalibrationUtil";

const getStages = (config) => {
  const gen5 = config.gen5;
  const handheld = config.timerSettings.handheld;
  const precisionCalibration = config.timerSettings.precisionCalibration;
  switch (gen5.timerMode) {
    case gen5TimerMode.DEFAULT:
      return secondTimerFactory.createStages(gen5.targetSecond, calibrationUtil.calibrateToMillis(gen5.calibration, precisionCalibration, handheld));
    case gen5TimerMode.C_GEAR:
      return delayTimerFactory.createStages(gen5.targetSecond, gen5.targetDelay, calibrationUtil.calibrateToMillis(gen5.calibration, precisionCalibration, handheld), handheld);
    case gen5TimerMode.ENTRALINK:
      return entralinkTimerFactory.createStages(
        gen5.targetSecond,
        gen5.targetDelay,
        calibrationUtil.calibrateToMillis(gen5.calibration, precisionCalibration, handheld),
        calibrationUtil.calibrateToMillis(gen5.entralinkCalibration, precisionCalibration, handheld),
        handheld
      );
    case gen5TimerMode.ENHANCED_ENTRALINK:
      return enhancedEntralinkTimerFactory.createStages(
        gen5.targetSecond,
        gen5.targetDelay,
        gen5.targetAdvances,
        calibrationUtil.calibrateToMillis(gen5.calibration, precisionCalibration, handheld),
        calibrationUtil.calibrateToMillis(gen5.entralinkCalibration, precisionCalibration, handheld),
        gen5.frameCalibration,
        handheld
      );
    default:
      return [];
  }
};

const calibrate = (config) => {
  const gen5 = config.gen5;
  const handheld = config.timerSettings.handheld;
  const precisionCalibration = config.timerSettings.precisionCalibration;
  const calibrationResult = {
    calibration: 0,
    entralinkCalibration: 0,
    frameCalibration: 0,
  };

  switch (gen5.timerMode) {
    case gen5TimerMode.DEFAULT:
      calibrationResult.calibration = (gen5.calibration || 0) + calibrationUtil.calibrateToDelays(secondCalibration(gen5.targetSecond, gen5.secondHit), precisionCalibration, handheld);
      break;
    case gen5TimerMode.C_GEAR:
      calibrationResult.calibration = (gen5.calibration || 0) + calibrationUtil.calibrateToDelays(delayCalibration(gen5.targetDelay, gen5.delayHit, handheld), precisionCalibration, handheld);
      break;
    case gen5TimerMode.ENTRALINK:
      calibrationResult.calibration = (gen5.calibration || 0) + calibrationUtil.calibrateToDelays(secondCalibration(gen5.targetSecond, gen5.secondHit), precisionCalibration, handheld);
      calibrationResult.entralinkCalibration = (gen5.entralinkCalibration || 0) + calibrationUtil.calibrateToDelays(entralinkCalibration(gen5.targetDelay, gen5.delayHit, gen5.targetSecond, gen5.secondHit, handheld), precisionCalibration, handheld);
      break;
    case gen5TimerMode.ENHANCED_ENTRALINK:
      calibrationResult.calibration = (gen5.calibration || 0) + calibrationUtil.calibrateToDelays(secondCalibration(gen5.targetSecond, gen5.secondHit), precisionCalibration, handheld);
      calibrationResult.entralinkCalibration = (gen5.entralinkCalibration || 0) + calibrationUtil.calibrateToDelays(entralinkCalibration(gen5.targetDelay, gen5.delayHit, gen5.targetSecond, gen5.secondHit, handheld), precisionCalibration, handheld);
      calibrationResult.frameCalibration = (gen5.frameCalibration || 0) + advancesCalibration(gen5.targetAdvances, gen5.actualAdvances);
      break;
    default:
      break;
  }
  return calibrationResult;
};

const secondCalibration = (targetSecond, secondHit) => {
  return secondTimerFactory.calibrate(targetSecond, secondHit);
};

const delayCalibration = (targetDelay, delayHit, handheld) => {
  return delayTimerFactory.calibrate(targetDelay, delayHit, handheld);
};

const entralinkCalibration = (targetDelay, delayHit, targetSecond, secondHit, handheld) => {
  return entralinkTimerFactory.calibrate(targetDelay, delayHit - secondCalibration(targetSecond, secondHit), handheld);
};

const advancesCalibration = (targetAdvances, actualAdvances) => {
  return enhancedEntralinkTimerFactory.calibrate(targetAdvances, actualAdvances);
};

export default {
  getStages,
  calibrate,
};
