$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
// SAVE ============================================

$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateProductForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#pid").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "ProductAPI",
type : type,
data : $("#formProduct").serialize(),
dataType : "text",
complete : function(response, status)
{
onProductSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#pid").val($(this).data("pid"));
$("#pname").val($(this).closest("tr").find('td:eq(0)').text());
$("#ptype").val($(this).closest("tr").find('td:eq(1)').text());
$("#description").val($(this).closest("tr").find('td:eq(2)').text());
$("#price").val($(this).closest("tr").find('td:eq(3)').text());
$("#quantity").val($(this).closest("tr").find('td:eq(4)').text());
$("#projId").val($(this).closest("tr").find('td:eq(5)').text());
});

function onProductSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#pid").val("");
$("#formProduct")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "ProductAPI",
type : "DELETE",
data : "pid=" + $(this).data("pid"),
dataType : "text",
complete : function(response, status)
{
onProductDeleteComplete(response.responseText, status);
}
});
});
function onProductDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divProductGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}
// CLIENT-MODEL================================================================
function validateProductForm()
{
// CODE
if ($("#pname").val().trim() == "")
{
return "Insert Product name.";
}
// NAME
if ($("#ptype").val().trim() == "")
{
return "Insert Product type.";
}
// PRICE-------------------------------
if ($("#description").val().trim() == "")
{
return "Insert Product description.";
}
// is numerical value
var tmpprice = $("#price").val().trim();
if (!$.isNumeric(price))
{
return "Insert a numerical value for Product price.";
}
// convert to decimal price
$("#price").val(parseFloat(tmpprice).toFixed(2));
// DESCRIPTION------------------------
if ($("#quantity").val().trim() == "")
{
return "Insert Product quantity.";
}
if ($("#projId").val().trim() == "")
{
return "Insert Product project Id.";
}
return true;
}