
$(document).ready(function() {
	var devices = $(".del-circuit");
	devices.each(function(){
		$(this).click(function(){
			var r = confirm("确认删除线路吗?");
			if (r == true) {
				var url = $(this).attr("href");
				window.location.href=url;
			} 
			return false;
		});
	});
})

$('#editCircuitModal').on('show.bs.modal', function(event) {
	var modal = $(this)
	var target = $(event.relatedTarget)
	var title = modal.find('#editCircuitModalTitle');
	if (target.data('option') == 'add') {
		title.text("添加线路");
		var collectorId = target.data('collector-id');
		modal.find('form').attr('action', '/add/circuit/' + collectorId);
	} else {
		title.text("编辑线路");
		
		var id = target.data('id')
		var number = target.data('number') // Extract info from data-*
		var light = target.data('light');
		var direction = target.data('direction');
		
		modal.find('#form-number').val(number);
		modal.find('#form-light').val(light);
		modal.find('#form-direction').val(direction);

		modal.find('form').attr('action', '/edit/circuit/' + id);
	}
});