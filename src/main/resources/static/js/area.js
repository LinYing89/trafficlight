
// 省\市\区对象
var provinces;

var provinceIndex = 0;
var cityIndex = 0;

function initSelectListener(){
	$("#select-province").change(function(){
		provinceIndex = $("#select-province").get(0).selectedIndex;
		var province = provinces[provinceIndex];
		var cities = province.childArea;
		setCitySelect(cities);
	})
	$("#select-city").change(function(){
		cityIndex = $("#select-city").get(0).selectedIndex;
		var province = provinces[provinceIndex];
		var cities = province.childArea;
		var districts = cities[cityIndex].childArea;
		setDistrictSelect(districts);
	})
}

//加载地址
function loadProvinces(districtId) {
	$.get('/provinces/' + districtId).done(function(data) {
		provinces = data;
		if(provinces.length > 0){
			for(var i = 0; i < provinces.length; i++){
				var province = provinces[i];
				var selectProvince = $("#select-province");
				var optionProvince = $("<option>" + province.areaName + "</option>");
				if(province.selected){
					optionProvince.attr("selected", "selected");
					provinceIndex = i;
				}
				selectProvince.append(optionProvince);
			}
		
			setCitySelect(provinces[provinceIndex].childArea);
		}
	});
	if(districtId != -1){
		loadDetailsAddress();
	}
}

function setCitySelect(cities){
	$("#select-city").empty();
	if(cities.length > 0){
		for(var j = 0; j < cities.length; j++){
			var city = cities[j];
			var selectCity = $("#select-city");
			var optionCity = $("<option>" + city.areaName + "</option>");
			if(city.selected){
				optionCity.attr("selected", "selected");
				cityIndex = j;
			}
			selectCity.append(optionCity);
		}
		setDistrictSelect(cities[cityIndex].childArea);
	}
}

function setDistrictSelect(districts){
	$("#select-district").empty();
	for(var k = 0; k < districts.length; k++){
		var district = districts[k];
		var selectDistrict = $("#select-district");
		var optionDistrict = $("<option>" + district.areaName + "</option>");
		optionDistrict.attr("value", district.areaId);
		if(district.selected){
			optionDistrict.attr("selected", "selected");
		}
		selectDistrict.append(optionDistrict);
	}
}