var dataLoadUrl = interUrl.basic + interUrl.function.list;
var resetDataUrl = interUrl.basic + interUrl.function.init;
var removeTableUrl =  interUrl.basic + interUrl.function.remove;
var form = "functionForm";
var module = "function";

$(document).ready(function() {
  $("#add_table").on("click", fnAddTable);
  $("#update_table").on("click", fnUpdateTable);
  $("#remove_table").on("click", fnRemoveTable);
  $("#query_table").on("click", fnQueryTable);
  $("#save_dialog").on("click", fnSaveDialog);
  $("#reset_data").on("click", fnResetData);
  $("#reset_form").on("click", fnResetForm);

  $("#pw_table").bootstrapTable('hideColumn', 'id');
  $("#pw_table").bootstrapTable('hideColumn', 'version');


    pageFunction();
});
