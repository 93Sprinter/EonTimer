<script>
  import CustomTimerTab from "./TimerTabs/CustomTimerTab.svelte";
  import Gen3TimerTab from "./TimerTabs/Gen3TimerTab.svelte";
  import FlowTimerTab from "./TimerTabs/FlowTimerTab.svelte";
  import Gen4TimerTab from "./TimerTabs/Gen4TimerTab.svelte";
  import Gen5TimerTab from "./TimerTabs/Gen5TimerTab.svelte";
  import { configStore } from "../stores/ConfigStore";
  import { timerMode } from "../constants/TimerMode";
  export let disabled;

  const setTimerMode = (e) => {
    configStore.update((v) => {
      v.currentTimerMode = e.target.dataset.timermode;
      return v;
    });
  };
</script>

<div class="d-flex flex-column flex-fill mb-3 min-h-0">
  <ul class="nav nav-tabs" id="timerTabs" role="tablist">
    <li class="nav-item" role="presentation">
      <button
        class="nav-link link-dark"
        {disabled}
        class:active={$configStore.currentTimerMode == timerMode.GEN5}
        on:click={setTimerMode}
        data-timermode={timerMode.GEN5}
        id="gen5-tab"
        data-bs-toggle="tab"
        data-bs-target="#gen5-tab-pane"
        type="button"
        role="tab">5</button
      >
    </li>
    <li class="nav-item" role="presentation">
      <button
        class="nav-link link-dark"
        {disabled}
        class:active={$configStore.currentTimerMode == timerMode.GEN4}
        on:click={setTimerMode}
        data-timermode={timerMode.GEN4}
        id="gen4-tab"
        data-bs-toggle="tab"
        data-bs-target="#gen4-tab-pane"
        type="button"
        role="tab">4</button
      >
    </li>
    <li class="nav-item" role="presentation">
      <button
        class="nav-link link-dark"
        {disabled}
        class:active={$configStore.currentTimerMode == timerMode.GEN3}
        on:click={setTimerMode}
        data-timermode={timerMode.GEN3}
        id="gen3-tab"
        data-bs-toggle="tab"
        data-bs-target="#gen3-tab-pane"
        type="button"
        role="tab">3</button
      >
    </li>
    <li class="nav-item" role="presentation">
      <button
        class="nav-link link-dark"
        {disabled}
        class:active={$configStore.currentTimerMode == timerMode.FLOW_TIMER}
        on:click={setTimerMode}
        data-timermode={timerMode.FLOW_TIMER}
        id="flowtimer-tab"
        data-bs-toggle="tab"
        data-bs-target="#flowtimer-tab-pane"
        type="button"
        role="tab">FT</button
      >
    </li>
    <li class="nav-item" role="presentation">
      <button
        class="nav-link link-dark"
        {disabled}
        class:active={$configStore.currentTimerMode == timerMode.CUSTOM}
        on:click={setTimerMode}
        data-timermode={timerMode.CUSTOM}
        id="custom-tab"
        data-bs-toggle="tab"
        data-bs-target="#custom-tab-pane"
        type="button"
        role="tab">C</button
      >
    </li>
  </ul>
  <div class="tab-content d-flex flex-column flex-fill p-2 min-h-0 border border-3 border-white bg-white bg-opacity-50 rounded-1 fs-085" id="timerTabsContent">
    <div class="tab-pane fade flex-fill min-h-0" class:show={$configStore.currentTimerMode == timerMode.GEN5} class:active={$configStore.currentTimerMode == timerMode.GEN5} id="gen5-tab-pane" role="tabpanel" tabindex="0">
      <Gen5TimerTab {disabled} />
    </div>
    <div class="tab-pane fade flex-fill min-h-0" class:show={$configStore.currentTimerMode == timerMode.GEN4} class:active={$configStore.currentTimerMode == timerMode.GEN4} id="gen4-tab-pane" role="tabpanel" tabindex="0">
      <Gen4TimerTab {disabled} />
    </div>
    <div class="tab-pane fade flex-fill min-h-0" class:show={$configStore.currentTimerMode == timerMode.GEN3} class:active={$configStore.currentTimerMode == timerMode.GEN3} id="gen3-tab-pane" role="tabpanel" tabindex="0">
      <Gen3TimerTab {disabled} />
    </div>
    <div class="tab-pane fade flex-fill min-h-0" class:show={$configStore.currentTimerMode == timerMode.FLOW_TIMER} class:active={$configStore.currentTimerMode == timerMode.FLOW_TIMER} id="flowtimer-tab-pane" role="tabpanel" tabindex="0">
      <FlowTimerTab {disabled} />
    </div>
    <div class="tab-pane fade flex-fill min-h-0" class:show={$configStore.currentTimerMode == timerMode.CUSTOM} class:active={$configStore.currentTimerMode == timerMode.CUSTOM} id="custom-tab-pane" role="tabpanel" tabindex="0">
      <CustomTimerTab {disabled} />
    </div>
  </div>
</div>

<style></style>
