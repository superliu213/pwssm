var dataLoadUrl = interUrl.basic + interUrl.role.list;
var resetDataUrl = interUrl.basic + interUrl.role.init;
var removeTableUrl =  interUrl.basic + interUrl.role.remove;
var form = "roleForm";
var module = "role";

var fnConfigureFunction = function(params) {
  if (!fnSelectOne()) {
    return;
  }

  var selections = $("#pw_table").bootstrapTable('getSelections');

  $("#save_tree").unbind("click");
  $("#save_tree").on("click", fnSaveTree);

  loadTree({
    "roleId": selections[0].roleId
  }, interUrl.basic + interUrl.role.function);
}

var fnSaveTree = function() {
  var selections = $("#pw_table").bootstrapTable('getSelections');

  var roleId;

  if (selections.length > 0) {
      roleId = selections[0].roleId;
  }else{
	  return;
  }

  var arr = $('#using_json_tree').jstree("get_checked", true);
  var ids = new Array();
  $.each(arr, function() {
      ids.push(this.data);
  });

  var data = {"roleId": roleId, "idstr": ids.join()}
  commonAjax(interUrl.basic + interUrl.role.rolefunction,
      "POST",data,
      function(){
        loadTree({
          "roleId": roleId
        }, interUrl.basic + interUrl.role.function)
      },
      false
  );
}

$(document).ready(function() {
  $("#add_table").on("click", fnAddTable);
  $("#update_table").on("click", fnUpdateTable);
  $("#remove_table").on("click", fnRemoveTable);
  $("#configure_function").on("click", fnConfigureFunction);
  $("#query_table").on("click", fnQueryTable);
  $("#save_dialog").on("click", fnSaveDialog);
  $("#open_tree").on("click", fnOpenTree);
  $("#close_tree").on("click", fnCloseTree);
  $("#reset_data").on("click", fnResetData);
  $("#reset_form").on("click", fnResetForm);

  $("#pw_table").bootstrapTable('hideColumn', 'id');
  $("#pw_table").bootstrapTable('hideColumn', 'version');

  pageFunction();
});
