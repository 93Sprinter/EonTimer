<script>
  import { timerRemaining, timerStages, timerCurrentStage } from "../stores/TimerStore";

  const formatter = new Intl.NumberFormat("de-DE", {
    useGrouping: false,
    signDisplay: "never",
    minimumFractionDigits: 3,
    maximumFractionDigits: 3,
  });

  const formatTime = (time) => {
    if (time >= 0 && time != Number.MAX_VALUE) {
      return formatter.format(time / 1000).replace(/[\.,]/, ":");
    }
    return "0:000";
  };
  const formatMinutes = (time) => {
    if (time >= 0 && time != Number.MAX_VALUE) {
      return "" + Math.floor(time / 1000 / 60);
    }
    return "0";
  };

  let remainingTime = "";
  let nextStageTime = "";
  let minuntesBeforTarget = "";

  $: remainingTime = formatTime($timerRemaining);
  $: nextStageTime = formatTime($timerStages[$timerCurrentStage]);
  $: minuntesBeforTarget = formatMinutes($timerRemaining);
</script>

<div class="border border-3 border-white bg-white bg-opacity-50 rounded-1 p-2">
  <div class="remainingTime ps-2 pe-2">{remainingTime}</div>
  <div>Minutes Before Target: {minuntesBeforTarget}</div>
  <div>{nextStageTime}</div>
</div>

<style>
  .remainingTime {
    font-size: 64px;
  }
</style>
