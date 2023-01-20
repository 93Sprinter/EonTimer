<script>
  import { configStore } from "../../stores/ConfigStore";

  export let disabled;

  let value;
  const addItem = () => {
    configStore.update((v) => {
      if (v.custom.stages) {
        v.custom.stages.push(value);
      }
      return v;
    });
    value = "";
  };
  const deleteItem = () => {
    configStore.update((v) => {
      if (v.custom.stages) {
        v.custom.stages.pop();
      }
      return v;
    });
  };
</script>

<div class="h-100 d-flex flex-column min-h-0">
  <div class="flex-fill overflow-y-auto overflow-x-hidden mb-2 border border-1 border-white bg-white bg-opacity-50 rounded-1">
    <table class="table table-light table-striped table-sm">
      <tbody>
        {#each $configStore.custom.stages as stage}
          <tr><td>{stage}</td></tr>
        {/each}
      </tbody>
    </table>
  </div>
  <div class="d-flex ">
    <input type="number" min="0" {disabled} bind:value class="form-control form-control-sm" />
    <button on:click={addItem} {disabled} class="btn btn-light btn-sm ms-1 link-dark" type="button"><i class="fa fa-plus" /></button>
    <button on:click={deleteItem} {disabled} class="btn btn-light btn-sm ms-1 link-dark" type="button"><i class="fa fa-minus" /></button>
  </div>
</div>

<style>
</style>
