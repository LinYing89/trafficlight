//地图右键菜单
var mapContextMenu;
// 右键点击的坐标点
var rightClickPoint;
// 信息框
var infoWindowDom;
var infoWindow;
var detailsAddressId;
// 定时刷新id
var refInterval;

$(document).ready(function() {
	try {
		initMap();
	} catch (e) {
	}

	createInfoWindowDom();
	createInfoWindow();
	
	//在area.js中
	initSelectListener();
	var districtId = $("#span-district-id").data("district-id");
	// 加载地址
	loadProvinces(districtId);

	var addAddressOptions = {
		success : addAddressSuccess,
		error : addAddresserror
	}
//	$('#form-add-address').ajaxForm(addAddressOptions);
	$('#form-area').ajaxForm(detailsAddressCallback);
})

// 查询详细地址回调函数
function detailsAddressCallback(data) {
	var divDetailsAddress = $("#div-details-address-nav");
	divDetailsAddress.empty();
	map.clearOverlays();
	for (var i = 0; i < data.length; i++) {
		var addressInfo = data[i];
		var dom_a = $('<a class="nav-link" href="#">'
				+ addressInfo.detailsAddress + '</a>');
		dom_a.data("district-id", addressInfo.districtId);
		dom_a.data("lng", addressInfo.lng);
		dom_a.data("lat", addressInfo.lat);
		divDetailsAddress.append(dom_a);
		dom_a.click(function() {
			var lng = $(this).data("lng");
			var lat = $(this).data("lat");
			var point = new BMap.Point(lng, lat);
			map.centerAndZoom(point, 16);
		})
		addMarker(addressInfo);
	}
}
function loadDetailsAddress(){
	var districtId = $("#span-district-id").data("district-id");
	$.post('/area/detailsAddress?districtId=' + districtId).done(function(data) {
		detailsAddressCallback(data);
	});
}

// 添加地址成功回调函数
function addAddressSuccess(data) {
	if (data.code != 0) {
		alert("添加错误  code:" + data.code + ",msg:" + data.msg);
		return;
	}
	var addressInfo = data.data;
	addMarker(addressInfo);
	$('#editAddressModal').modal('hide');
	// addDetailsAddressNav();
	// alert("success " + data);
}
// 添加地址错误回调函数
function addAddresserror(data) {
	$('#editAddressModal').modal('hide');
	alert("error " + data);
}

// 添加标志物
function addMarker(addressInfo) {
	var point = new BMap.Point(addressInfo.lng, addressInfo.lat)
	var myCompOverlay = new TrafficLightOverlay(point, addressInfo);
	map.addOverlay(myCompOverlay);
}

function initMap() {
	map = new BMap.Map("container");
	// 创建地图实例
	var point = new BMap.Point(119.226, 34.613);
	// 创建点坐标
	map.centerAndZoom(point, 15);
	// 初始化地图，设置中心点坐标和地图级别
	map.enableScrollWheelZoom(true);

	// var ctrl = new BMap.NavigationControl();
	var opts = {
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT
	}
	map.addControl(new BMap.NavigationControl(opts));
	map.addControl(new BMap.ScaleControl());
	map.addControl(new BMap.OverviewMapControl());
	map.setCurrentCity("连云港");

	// 添加右键菜单
	mapContextMenu = new BMap.ContextMenu();
	mapContextMenu.addItem(new BMap.MenuItem('添加地址', mapMenuAddAddressClick,
			100));
	map.addContextMenu(mapContextMenu);// 给标记添加右键菜单

	// 右键点击获取经纬度
	map.addEventListener("rightclick", function(e) {
		rightClickPoint = e.point;
	});

	TrafficLightOverlay.prototype = new BMap.Overlay();
	TrafficLightOverlay.prototype.initialize = function(map) {
		this._map = map;
		var div = this._div = document.createElement("div");
		div.style.position = "absolute";
		div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
		// div.style.backgroundColor = "#EE5D5B";
		// div.style.border = "1px solid #BC3B3A";
		div.style.color = "red";
		// div.style.height = "40px";
		// div.style.width = "40px";
		div.style.lineHeight = "18px";
		div.style.whiteSpace = "nowrap";
		div.style.MozUserSelect = "none";
		div.style.fontSize = "12px";

		var img = this._image = document.createElement("img");
		div.appendChild(img);
		img.setAttribute("src", "/img/marker_red.png");
		img.style.width = "28px";
		img.style.height = "36px";

		var span = this._span = document.createElement("span");
		div.appendChild(span);
		span.appendChild(document
				.createTextNode(this._addressInfo.detailsAddress));
		span.setAttribute("hidden", "hidden");

		var that = this;

		var arrow = this._arrow = document.createElement("div");

		arrow.style.position = "absolute";
		arrow.style.width = "11px";
		arrow.style.height = "10px";
		arrow.style.top = "22px";
		arrow.style.left = "10px";
		arrow.style.overflow = "hidden";
		div.appendChild(arrow);

		div.onmouseover = function() {
			this.getElementsByTagName("span")[0].removeAttribute("hidden");
			this.style.cursor = "pointer";
		}

		div.onmouseout = function() {
			this.getElementsByTagName("span")[0].setAttribute("hidden",
					"hidden");
			this.style.cursor = "hand"
		}

		var _time = null;
		$(div).dblclick(function() {
			clearTimeout(_time);
			var districtId = that._addressInfo.districtId;
			window.location.href = "/data/" + that._addressInfo.districtId;
			// 返回false, 终止路由, 防止map放大
			return false;
		})
		$(div).click(
				function() {
					clearTimeout(_time);
					// 单机延迟执行, 防止双击无法执行
					_time = setTimeout(function() {
						// 单击事件在这里
						detailsAddressId = that._addressInfo.detailsAddressId;
						showInfoWindow(that._map,
								that._addressInfo.detailsAddress, that._point);
					}, 300);
					return true;
				})

		map.getPanes().labelPane.appendChild(div);

		return div;
	}
	TrafficLightOverlay.prototype.draw = function() {
		var map = this._map;
		var pixel = map.pointToOverlayPixel(this._point);
		this._div.style.left = pixel.x - parseInt(this._arrow.style.left)
				+ "px";
		this._div.style.top = pixel.y - 30 + "px";
	}
	// window.map = map;// 将map变量存储在全局
}

function showInfoWindow(map, title, point) {
	// var opts = {
	// width : 340, // 信息窗口宽度
	// height : 240, // 信息窗口高度
	// offset : new BMap.Size(0, -30), // 偏移, 防止盖住marker
	// title : title
	// }
	// refreshInfoWindowDom(device);
	// var infoWindow = new BMap.InfoWindow(infoWindowDom, opts); // 创建信息窗口对象
	// var infoWindow = new BMap.InfoWindow("", opts); // 创建信息窗口对象
	infoWindow.setTitle(title);
	map.openInfoWindow(infoWindow, point);
	// marker.openInfoWindow(infoWindow);
	// infoWindow.addEventListener("open", function(e) {
	// alert("open");
	// });
	// infoWindow.open = function() {
	// alert("open");
	// }
	// infoWindow.close = function() {
	// alert("close");
	// }
}

// 地图右键菜单, 添加地址点击事件
function mapMenuAddAddressClick() {
	var geoc = new BMap.Geocoder();
	geoc.getLocation(rightClickPoint, function(rs) {
		var addComp = rs.addressComponents;
		var model = $('#editAddressModal');
		model.find("#form-province").val(addComp.province);
		model.find("#form-city").val(addComp.city);
		model.find("#form-district").val(addComp.district);
		model.find("#form-lng").val(rightClickPoint.lng);
		model.find("#form-lat").val(rightClickPoint.lat);
		model.find("#form-detailsAddress").val(addComp.street);

		$('#editAddressModal').modal('show');
		// alert(addComp.province + ", " + addComp.city + ", " +
		// addComp.district + ", " + addComp.street + ", " +
		// addComp.streetNumber);
	});
}

// 自定义信号灯标注点图标
function TrafficLightOverlay(point, addressInfo) {
	this._point = point;
	this._addressInfo = addressInfo;
}
function createInfoWindow() {
	if(!BMap){
		return;
	}
	var opts = {
		width : 340, // 信息窗口宽度
		height : 200, // 信息窗口高度
		offset : new BMap.Size(0, -30)
	}
	// refreshInfoWindowDom(device);
	infoWindow = new BMap.InfoWindow(infoWindowDom, opts); // 创建信息窗口对象
	infoWindow.addEventListener("open", function(e) {
		console.info("open");
		startRefreshInfoWindow();
	});
	infoWindow.addEventListener("close", function(e) {
		console.info("close");
		stopRefreshInfoWindow();
	});
}
function createInfoWindowDom() {
	infoWindowDom = $("<div><hr/></div>");
	infoWindowDom = infoWindowDom.get(0);
	return infoWindowDom;
}

function startRefreshInfoWindow() {
	console.info("load start");
	loadDatas();
	refInterval = window.setInterval(loadDatas, 8000);
}
function stopRefreshInfoWindow() {
//	console.info("load stop");
	window.cleanInterval(refInterval);
}
function loadDatas() {
//	console.info("load");
	if (detailsAddressId != null) {
		$(infoWindowDom).load("/map/infowidow/" + detailsAddressId);
	}
}