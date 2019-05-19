$(document).ready(function() {
	var devices = $(".del-msg-manager");
	devices.each(function() {
		$(this).click(function() {
			var r = confirm("确认删除通信机吗?");
			if (r == true) {
				var url = $(this).attr("href");
				window.location.href = url;
			}
			return false;
		});
	});
	$("#del-details-address").click(function(){
		var r = confirm("确认删除地址吗?");
		if (r == true) {
			var url = $(this).attr("href");
			window.location.href = url;
		}
		return false;
	});

	var addMsgManagerOptions = {
		success : addMsgManagerSuccess,
		error : addMsgManagerError
	}
	$('#form-edit-msgmanager').ajaxForm(addMsgManagerOptions);
})

function addMsgManagerSuccess(result) {
	if (result.code == 0) {
		$("#editMsgManagerModal").modal('hide');
		var detailsAddressId = $("#a-details-address-id").data(
				"details-address-id");
		$('#tbody-msgmanager').load("/get/msgManagers/" + detailsAddressId);
	}else{
		alert(result.msg);
	}
}

function addMsgManagerError() {

}

$('#editMsgManagerModal').on(
		'show.bs.modal',
		function(event) {
			var modal = $(this)
			var target = $(event.relatedTarget)
			var title = modal.find('#editMsgManagerModalTitle');
			if (target.data('option') == 'add') {
				title.text("添加通信管理机");
				var detailsAddressId = target.data('details-address-id');
				modal.find('form').attr('action',
						'/add/msgManager/' + detailsAddressId);
			} else {
				title.text("编辑通信管理机");
				var id = target.data('id');
				var code = target.data('code');

				 modal.find('#manager-code').val(code);

				modal.find('form').attr('action', '/edit/msgManager/' + id);
			}
		});