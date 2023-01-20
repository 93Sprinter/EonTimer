import { writable } from "svelte/store";

export const timerStages = writable([]);
export const timerRemaining = writable(0);
export const timerRunning = writable(false);
export const timerCurrentStage = writable(0);
export const timerPrimed = writable(false);

export const timerStoreReset = () => {
  timerStages.set([]);
  timerRemaining.set(0);
  timerRunning.set(false);
  timerCurrentStage.set(0);
  timerPrimed.set(false);
};
