var districtId = null;

$(document).ready(function() {
	
	//在area.js中
	initSelectListener();
	var districtId = $("#span-district-id").data("district-id");
	//加载地址
	loadProvinces(districtId);
	
	loadDetailsAddress();
	$('#form-area').ajaxForm(detailsAddressCallback);
	
	//每5秒加载一个区的数据
	window.setInterval(loadDatas,8000);
})

//加载一个区数据
function loadDatas(){
	var districtId = $("#select-district").val();
	if(districtId != null){
		$('#div-datagroups').load("/dataGroups/"+ districtId);
	}
}

//查询详细地址回调函数
function detailsAddressCallback(data){
	var districtId = $("#select-district").val();
	$("#span-district-id").data("district-id", districtId);
	var divDetailsAddress = $("#div-details-address-nav");
	divDetailsAddress.empty();
	for(var i = 0; i < data.length; i++){
		var addressInfo = data[i];
		if(districtId == null){
			districtId = addressInfo.districtId;
		}
		var dom_a = $('<a class="nav-link" href="#">' + addressInfo.detailsAddress + '</a>');
		dom_a.data("district-id", addressInfo.districtId);
		divDetailsAddress.append(dom_a);
		dom_a.click(function() {
//			var districtId = $(this).data("district-id");
//			$('#div-datagroups').load("/dataGroups/"+ districtId);
		})
	}
	loadDatas();
}

function loadDetailsAddress(){
	var districtId = $("#span-district-id").data("district-id");
	$.post('/area/detailsAddress?districtId=' + districtId).done(function(data) {
		detailsAddressCallback(data);
	});
}
