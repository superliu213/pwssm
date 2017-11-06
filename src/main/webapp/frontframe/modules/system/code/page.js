var dataLoadUrl = interUrl.basic + interUrl.system.list;
var form = "codeForm";

var fnCreateCode = function(params){
    if (!fnSelectMore()) {
        return;
    }
    var form = $("#codeForm").values();
    var buttons = new Array();

    for(var item in form){
        if(!(item == "tableName" || item == "tableComment")){
            buttons.push(form[item]);
        }
    }

    var selections = $("#pw_table").bootstrapTable('getSelections');
    var tables = new Array();

    $.each(selections, function(i, row) {
        tables[i] = row['tableName'];
    });

    var data = {"buttons":JSON.stringify(buttons),"tables":JSON.stringify(tables)}

    location.href = interUrl.basic + interUrl.system.create + "?buttons=" + JSON.stringify(buttons)+"&tables=" + JSON.stringify(tables);
}

$(document).ready(function() {
  $("#create_code").on("click", fnCreateCode);
  $("#query_table").on("click", fnQueryTable);
  $("#reset_form").on("click", fnResetForm);

  pageFunction();
});
