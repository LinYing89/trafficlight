
$(document).ready(function() {
	var devices = $(".del-collector");
	devices.each(function(){
		$(this).click(function(){
			var r = confirm("确认删除采集终端吗?");
			if (r == true) {
				var url = $(this).attr("href");
				window.location.href=url;
			} 
			return false;
		});
	});
})

$('#editCollectorModal').on('show.bs.modal', function(event) {
	var modal = $(this)
	var target = $(event.relatedTarget)
	var title = modal.find('#editCollectorModalTitle');
	if (target.data('option') == 'add') {
		title.text("添加采集终端");
		var msgManagerId = target.data('msg-manager-id');
		modal.find('form').attr('action', '/add/collector/' + msgManagerId);
	} else {
		title.text("编辑采集终端");
		
		var id = target.data('id')
		var busCode = target.data('bus-code') // Extract info from data-*
		var code = target.data('code');
		var beginAddress = target.data('begin-address');
		var dataLength = target.data('data-length');
		var dataType = target.data('data-type-enum')
		
		modal.find('#form-bus-code').val(busCode)
		modal.find('#form-code').val(code)
		modal.find('#form-begin-address').val(beginAddress)
		modal.find('#form-data-length').val(dataLength)
		modal.find('#form-data-type').val(dataType)

		modal.find('form').attr('action', '/edit/collector/' + id);
	}
});