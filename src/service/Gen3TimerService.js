import { gen3TimerMode } from "../constants/Gen3TimerMode";
import fixedFrameTimerFactory from "./factory/FixedFrameTimerFactory";
import variableFrameTimerFactory from "./factory/VariableFrameTimerFactory";

const getStages = (config) => {
  const gen3 = config.gen3;
  const handheld = config.timerSettings.handheld;
  switch (gen3.timerMode) {
    case gen3TimerMode.DEFAULT:
      return fixedFrameTimerFactory.createStages(gen3.preTimer, gen3.targetFrame, gen3.calibration, handheld);
    case gen3TimerMode.VARIABLE:
      return variableFrameTimerFactory.createStages(gen3.preTimer);
    default:
      return [];
  }
};

const calibrate = (config) => {
  const gen3 = config.gen3;
  const handheld = config.timerSettings.handheld;
  return (gen3.calibration || 0) + fixedFrameTimerFactory.calibrate(gen3.targetFrame, gen3.frameHit, handheld);
};

export default {
  getStages,
  calibrate,
};
