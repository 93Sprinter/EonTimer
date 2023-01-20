<script>
  import { configStore } from "../stores/ConfigStore";
  import { actionMode } from "../constants/ActionMode";
  import { sound } from "../constants/Sound";
  import { handheld } from "../constants/Handheld";

  export let disabled;
</script>

<button type="button" class="btn btn-light btn-sm link-dark" {disabled} data-bs-toggle="modal" data-bs-target="#settingsModal">
  <i class="fa fa-cog" />
</button>

<div class="modal fade" id="settingsModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="settingsModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <ul class="nav nav-tabs" id="settingsTabs" role="tablist">
          <li class="nav-item active" role="presentation">
            <button class="nav-link active" id="action-tab" data-bs-toggle="tab" data-bs-target="#action-tab-pane" type="button" role="tab" aria-controls="action-tab-pane" aria-selected="true">Action</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="time-tab" data-bs-toggle="tab" data-bs-target="#time-tab-pane" type="button" role="tab" aria-controls="time-tab-pane" aria-selected="false">Time</button>
          </li>
        </ul>
        <div class="tab-content" id="settingsTabsContent">
          <div class="tab-pane fade show active" id="action-tab-pane" role="tabpanel" aria-labelledby="action-tab" tabindex="0">
            <div class="p-2">
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="actionMode">Mode</label>
                <div class="col-sm-7">
                  <select id="actionMode" class="form-select form-select-sm" bind:value={$configStore.actionSettings.mode}>
                    {#each Object.keys(actionMode) as a}
                      <option value={a}>{a}</option>
                    {/each}
                  </select>
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="sound">Sound</label>
                <div class="col-sm-7">
                  <select id="sound" class="form-select form-select-sm" bind:value={$configStore.actionSettings.sound}>
                    {#each Object.keys(sound) as s}
                      <option value={s}>{s}</option>
                    {/each}
                  </select>
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="color">Color</label>
                <div class="col-sm-7">
                  <input id="color" type="color" class="form-control form-control-sm form-control-color" bind:value={$configStore.actionSettings.color} />
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="interval">Interval</label>
                <div class="col-sm-7">
                  <input id="interval" type="number" min="0" class="form-control form-control-sm" bind:value={$configStore.actionSettings.interval} />
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="count">Count</label>
                <div class="col-sm-7">
                  <input id="count" type="number" min="0" class="form-control form-control-sm" bind:value={$configStore.actionSettings.count} />
                </div>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="time-tab-pane" role="tabpanel" aria-labelledby="time-tab" tabindex="0">
            <div class="p-2">
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="console">Console</label>
                <div class="col-sm-7">
                  <select id="handheld" class="form-select form-select-sm" bind:value={$configStore.timerSettings.handheld}>
                    {#each Object.keys(handheld) as h}
                      <option value={h}>{h}</option>
                    {/each}
                  </select>
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="refreshInterval">Refresh Interval</label>
                <div class="col-sm-7">
                  <input id="refreshInterval" type="number" min="0" class="form-control form-control-sm" bind:value={$configStore.timerSettings.refreshInterval} />
                </div>
              </div>
              <div class="row mb-1">
                <label class="col-sm-5 col-form-label" for="precisionCalibration">Precision Calibration</label>
                <div class="col-sm-7">
                  <input id="precisionCalibration" type="checkbox" class="form-check-input" bind:checked={$configStore.timerSettings.precisionCalibration} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary btn-sm link-white" data-bs-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>

<style></style>
