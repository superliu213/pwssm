var dataLoadUrl = interUrl.basic + interUrl.group.list;
var resetDataUrl = interUrl.basic + interUrl.group.init;
var removeTableUrl =  interUrl.basic + interUrl.group.remove;
var form = "groupForm";
var module = "group";

var fnConfigureRole = function(params) {
  if (!fnSelectOne()) {
    return;
  }
  var selections = $("#pw_table").bootstrapTable('getSelections');
  
  $("#save_tree").unbind("click");
  $("#save_tree").on("click", fnSaveTree);
  
  loadTree({
    "groupId": selections[0].groupId
  }, interUrl.basic + interUrl.group.role);
}

var fnSaveTree = function() {

  var selections = $("#pw_table").bootstrapTable('getSelections');

  var groupId;

  if (selections.length > 0) {
    groupId = selections[0].groupId;
  }else{
	  return;
  }

  var arr = $('#using_json_tree').jstree("get_checked", true);
  var ids = new Array();
  $.each(arr, function() {
    ids.push(this.data);
  });

  commonAjax(interUrl.basic + interUrl.group.grouprole,
      "POST",
      {
        "groupId": groupId,
        "idstr": ids.join()
      },
      function(){
        loadTree({
          "groupId": groupId,
        }, interUrl.basic + interUrl.group.role)
      }
  )
}

$(document).ready(function() {
  $("#add_table").on("click", fnAddTable);
  $("#update_table").on("click", fnUpdateTable);
  $("#remove_table").on("click", fnRemoveTable);
  $("#configure_role").on("click", fnConfigureRole);
  $("#query_table").on("click", fnQueryTable);
  $("#save_dialog").on("click", fnSaveDialog);
  $("#reset_data").on("click", fnResetData);
  $("#reset_form").on("click", fnResetForm);

  $("#pw_table").bootstrapTable('hideColumn', 'id');
  $("#pw_table").bootstrapTable('hideColumn', 'version');

    pageFunction();
});
