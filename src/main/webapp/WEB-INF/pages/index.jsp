<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>해오름 농장</title>
 	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://code.highcharts.com/stock/highstock.js"></script>
<%--<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>--%>
<script src="https://code.highcharts.com/stock/modules/data.js"></script>
<script src="https://code.highcharts.com/stock/modules/drilldown.js"></script>
<script type="text/javascript" src="http://cdn.jsdelivr.net/raphael/2.1.2/raphael-min.js"></script>

<script type="text/javascript" src="https://rawgit.com/toorshia/justgage/master/justgage.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>

<script src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>

<link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css
">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<style type="text/css">
	.setting_wrapper {
         width: 100%;
         padding-left:5px;
         margin:5px;
    }
    .setting_wrapper .setting_input {
         width: auto ;
         height: 30px;
         float:left;
         line-height:32px;
         margin:5px;
    }
    .component_wrapper {
         width: 100%;
    }
    .component_wrapper .row {
        border: 4px double #acacac;
        margin-bottom: 7px;
    }
    .component_wrapper hr {
        margin: 0px 15px 0px 15px;
        border-top: 2px dotted #ccc
    }
    .temp1, .temp2, .hum1, .hum2, .weight1, .weight2, .flux1, flux2 {
        height: 290px;
    }

	.form-control, .input-group-addon {
        width: auto;
    }
	.col-md-5, .col-sm-12 {
		padding-left: 0px ;
		padding-right: 0px ;
	}
    
    h4.title { 
        text-align: center;
    }

	hr {
        border-top: 1px solid #acacac
    }
</style>

<script type="text/javaScript">
Highcharts.setOptions({
    lang: {
        thousandsSep: ','
    },
    global: {
        useUTC: false
    }

});
var dongs = {
    "1" : [
        {
            "name" : "1동 온습도1",
            "agent_code" : "996FBB5D-7436-4A22-AF9D-C56504A2478B",
            "type": ["EA010", "EA009"],
            "id": "temp_hum1",
            "templet": "temp_hum"
        },
        {
            "name" : "1동 온습도2",
            "agent_code" : "022F035D-DF75-4A9C-924E-DA61D82E3214",
            "type": ["EA010", "EA009"],
            "id": "temp_hum2",
            "templet": "temp_hum"
        },
        {
            "name" : "1동 중량1",
            "agent_code" : "41C3E71F-4EF0-48BD-AAAE-D43C49A3C29F",
            "type": "EA007_E03_SE03",
            "id": "weight1",
            "templet": "weight"
        },
        {
            "name" : "1동 중량2",
            "agent_code" : "4C8605E9-2FC0-4506-9D58-BC8619F3AAB0",
            "type": ["EA007_E03_SE03"],
            "id": "weight2",
            "templet": "weight"
        },
        {
            "name" : "1동 유량1",
            "agent_code" : "272F057C-D7F6-4960-8FA1-F7A809B323BF",
            "type": ["EA007_E03_SE04"],
            "id": "flux1",
            "templet": "flux"
        },
        {
            "name" : "1동 유량2",
            "agent_code" : "E06FE0F6-A4D5-40AA-AB7F-56FF787A1B95",
            "type": ["EA007_E03_SE04"],
            "id": "flux2",
            "templet": "flux"
        }
    ],
    "2": [
        {
            "name" : "2동 온습도1",
            "agent_code" : "10B18372-D3B9-4C2D-8B47-EE0165FE3A52",
            "type": ["EA010", "EA009"],
            "id": "temp_hum1",
            "templet": "temp_hum"
        },
        {
            "name" : "2동 온습도2",
            "agent_code" : "68DAE242-41FB-4EF3-8528-A42AE45308FA",
            "type": ["EA010", "EA009"],
            "id": "temp_hum2",
            "templet": "temp_hum"
        },
        {
            "name" : "2동 중량",
            "agent_code" : "18EE5BD3-3AEB-49D0-AE48-E630C758051D",
            "type": ["EA007_E03_SE03"],
            "id": "weight1",
            "templet": "weight"
        },
        {
            "name" : "2동 유량",
            "agent_code" : "6BBC4A35-A50D-458A-9663-9F324BAEFFEF",
            "type": ["EA007_E03_SE04"],
            "id": "flux1",
            "templet": "flux"
        }
    ]
} 

function search(dong){


    var components = dongs[dong];

    $(".component_wrapper").empty();

    $.each(components, function(key, value){
        var templet = value.templet;

        var templet_html = $(".templet").find("." + templet + ":first").html()
        $(".component_wrapper").append(templet_html);
        var element = $(".component_wrapper").find(".row." + templet + ":last");

        element.find(".title").text(value.name)

        if(templet === "temp_hum"){
            renderTemp_hum(element, dong, value);
        }
        else if(templet === "weight"){
            renderWeight(element, dong, value);

        }
        else if(templet === "flux"){
            renderFlux(element, dong, value);
        }
    });
}

function renderTemp_hum(element, dong, obj){
    element.find(".temp1").attr("id", "temp1_" + obj.agent_code)
    element.find(".hum1").attr("id", "hum1_" + obj.agent_code)
    element.find(".temp2").attr("id", "temp2_" + obj.agent_code)
    element.find(".hum2").attr("id", "hum2_" + obj.agent_code)

    $.getJSON("/stat/temperature/" + dong + ".json",
        function (data) {
            var agent_code = obj.agent_code;
            var temp_array = [];
            $.each(data.Temperatures.temperature, function(key, value){
                if(agent_code === value.agentcode){
                    temp_array.push([value.time, value.val]);
                }
            });

            var series = {
                "name": "Temperature",
                "data": temp_array,
                "color": "#ED561B"
            }

            $("#temp2_" + obj.agent_code).highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: ''
                },
                xAxis: {
                    type: 'datetime',
                    dateTimeLabelFormats: { // don't display the dummy year
                        month: '%m-%d',
                        day: '%m-%d',
                        year: '%Y'
                    }
                },
                credits: {
                  enabled: false
                },
                navigator: {
                    enabled: true,
                    height: 15
                },
                yAxis: {
                    title: {
                        text: 'Temperature (℃)'
                    },
                    min: 0,
                    plotLines: [{
                        id: 'limit-min',
                        color: '#0000FF',
                        dashStyle: 'ShortDash',
                        width: 2,
                        value: 10,
                        zIndex: 0,
                        label: {
                            text: "낮음"
                        }
                    }, {
                        id: 'limit-max',
                        color: '#FF0000',
                        dashStyle: 'ShortDash',
                        width: 2,
                        value: 33,
                        zIndex: 0,
                        label: {
                            text: "높음"
                        }
                    }]
                },
                tooltip: {
                    headerFormat: '<b>{series.name}</b><br>',
                    pointFormat: '{point.x:%H:%M}: {point.y:,.2f} ℃'
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    spline: {
                        marker: {
                            enabled: false
                        }
                    }
                },
                series: [series]
            });

            var lastest_value = 0;
            try {
                lastest_value = series.data[series.data.length -1][1];
            } catch(e) {
                lastest_value = 0;
            }

            new JustGage({
                id: "temp1_" + obj.agent_code,
                title: "Temperature",
                value: lastest_value,
                min: 0,
                max: 50,
                textRenderer: function(a){return a + '℃'},
                shadowOpacity: 0,
                relativeGaugeSize: true
            });
        }
    );

    $.getJSON("/stat/humidity/" + dong + ".json",
        function (data) {
            var agent_code = obj.agent_code;
            var temp_array = [];
            $.each(data.Humidities.humidity, function(key, value){
                if(agent_code === value.agentcode){
                    temp_array.push([value.time, value.val]);
                }
            });

            var series = {
                "name": "Humidity",
                "data": temp_array
            }

            $("#hum2_" + obj.agent_code).highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: ''
                },
                xAxis: {
                    type: 'datetime',
                    dateTimeLabelFormats: { // don't display the dummy year
                        month: '%m-%d',
                        day: '%m-%d',
                        year: '%Y'
                    }
                },
                credits: {
                    enabled: false
                },
                navigator: {
                    enabled: true,
                    height: 15
                },
                yAxis: {
                    title: {
                        text: 'Humidity (%)'
                    },
                    min: 0,
                    plotLines: [{
                        id: 'limit-min',
                        color: '#0000FF',
                        dashStyle: 'ShortDash',
                        width: 1,
                        value: 10,
                        zIndex: 0,
                        label: {
                            text: "낮음"
                        }
                    }, {
                        id: 'limit-max',
                        color: '#FF0000',
                        dashStyle: 'ShortDash',
                        width: 1,
                        value: 60,
                        zIndex: 0,
                        label: {
                            text: "높음"
                        }
                    }]
                },
                tooltip: {
                    headerFormat: '<b>{series.name}</b><br>',
                    pointFormat: '{point.x:%H:%M}: {point.y:.2f} %'
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    spline: {
                        marker: {
                            enabled: false
                        }
                    }
                },
                series: [series]
            });

            var lastest_value = 0;
            try {
                lastest_value = series.data[series.data.length -1][1];
            } catch(e) {
                lastest_value = 0;
            }

            new JustGage({
                id: "hum1_" + obj.agent_code,
                title: "Humidity",
                value: lastest_value,
                min: 0,
                max: 70,
                textRenderer: function(a){return a + '%'},
                shadowOpacity: 0,
                relativeGaugeSize: true
            });
        }
    );
}

function renderWeight(element, dong, obj){

    var temp_standard = {}
    $.getJSON("/stat/standard.json",
        function (data) {
            $.each(data.ChickManual.table1, function(i, v){
                temp_standard[v.date] = v.weight;
            })
        }
    );
    $.getJSON("/stat/weight/" + dong + ".json",
        function (data) {
            var agent_code = obj.agent_code;
            var temp_array = [];
            var standard_array = [];

            $.each(data.Weights.weight, function(key, value){
                if(agent_code === value.agentcode){
                    temp_array.push([new Date(value.time).getTime(), value.val]);
                }
            });

            $.each(temp_array, function(i, v){
                standard_array.push([v[0], temp_standard[i + 1]]);
            })


            var series = {
                "name": "Weight",
                "data": temp_array,
                "color": "#D28137"
//                "pointWidth": 30
            }
            var standard_series = {
                "name": "권장",
                "type": "line",
                "data": standard_array,
                "color": "#434348"
            }

            element.find(".weight1").highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: ''
                },
                xAxis: {
                    type: 'datetime',
                    dateTimeLabelFormats: { // don't display the dummy year
                        month: '%m-%d',
                        day: '%m-%d',
                        year: '%Y'
                    },
                    range: 10 * 24 * 3600 * 1000
                },
                credits: {
                    enabled: false
                },
                yAxis: {
                    title: {
                        text: 'Weight (g)'
                    },
                    min: 0
                },
                tooltip: {
                    headerFormat: '<b>{series.name}</b><br>',
                    pointFormat: '{point.x:%m-%d}: {point.y:.1f} g'
                },
                legend: {
                    enabled: true,
                    align: 'left',
                    backgroundColor: '#FFFFFF',
                    borderColor: 'black',
                    borderWidth: 1,
                    layout: 'vertical',
                    verticalAlign: 'top',
                    floating:true,
                    x: 70,
//                    y: 100,
                    shadow: true
                },
                navigator: {
                    enabled: true,
                    height: 15
                },
                plotOptions: {
                    column: {
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:,.0f} L',
                            rotation: -90,
                            color: '#FFFFFF',
                            align: 'right',
                            y: 10,
                            allowOverlap:true,
                            overflow: "none"
                        }
                    }
                },
                series: [series, standard_series]
            });
        }
    );
}

function renderFlux(element, dong, obj){

    var temp_standard = {}
    $.getJSON("/stat/standard.json",
            function (data) {
                $.each(data.ChickManual.table1, function(i, v){
                    temp_standard[v.date] = v.water;
                })
            }
    );


    $.getJSON("/stat/flux/" + dong + ".json",
        function (data) {
            var agent_code = obj.agent_code;
            var temp_array = [];
            var standard_array = [];

            $.each(data.Fluxes.flux, function(key, value){
                if(agent_code === value.agentcode){
                    temp_array.push([new Date(value.time).getTime(), value.val]);
                }
            });

            $.each(temp_array, function(i, v){
                standard_array.push([v[0], temp_standard[i + 1]]);
            })

            var series = {
                "name": "Water",
                "data": temp_array,
//                "pointWidth": 15
            }
            var standard_series = {
                "name": "권장",
                "type": "line",
                "data": standard_array
            }

            element.find(".flux1").highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: ''
                },
                xAxis: {
                    type: 'datetime',
                    dateTimeLabelFormats: { // don't display the dummy year
                        month: '%m-%d',
                        day: '%m-%d',
                        year: '%Y'
                    },
                    range: 10 * 24 * 3600 * 1000
                },
                credits: {
                    enabled: false
                },
                yAxis: {
                    title: {
                        text: 'Water'
                    },
                    min: 0
                },
                tooltip: {
                    headerFormat: '<b>{series.name}</b><br>',
                    pointFormat: '{point.x:%m-%d}: {point.y:,.0f} L'
                },
                legend: {
                    enabled: true,
                    align: 'left',
                    backgroundColor: '#FFFFFF',
                    borderColor: 'black',
                    borderWidth: 1,
                    layout: 'vertical',
                    verticalAlign: 'top',
                    floating:true,
                    x: 70,
//                    y: 100,
                    shadow: true
                },
                navigator: {
                    enabled: true,
                    height: 15
                },
                plotOptions: {
                    column: {
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:,.0f} L',
                            rotation: -90,
                            color: '#FFFFFF',
                            align: 'right',
                            y: 10,
                            allowOverlap:true,
                            overflow: "none"
                        }
                    }
                },
                series: [series, standard_series]
            });
        }
    );
}





$(function () {
    search("1")

    $("#search").click(function(){
        search($("#dong").val(), $("#stime").val(), $("#etime").val());
    });

// Create the chart
    
});
</script>
</head>
	<body>
		<div class="container-fluid" style="padding:20px">
			<div class="row">
			  <div class="col-sm-12">
			  	<h4>해오름 농장</h4>
			  	<hr />
			  </div>
			</div>		
			<div class="setting_wrapper">	
				<div class="row">
				  <div class="col-sm-12">
				  	<div class="form-group pull-right">
				  		<div class="setting_input">
					  		<label>검색조건 설정</label>
					  	</div>
					  	<div class="setting_input">
							<select class="form-control" style="margin-bottom: 15px;" id="dong">
							    <option value="1">1동</option>
							    <option value="2">2동</option>
						  	</select>
						</div>
						<div class="setting_input">
						  <button type="button" class="btn btn-default pull-right" id="search">검색</button>
						</div>
					</div>
				  </div>
				</div>
			</div>
			<div class="component_wrapper"></div>
		</div>

        <div style="display:none" class="templet">
            <div class="temp_hum">
                <div class="row temp_hum">
                    <h4 class="title"></h4>
                    <div class="col-sm-4">
                        <div class="temp1"></div>
                    </div>
                    <div class="col-sm-8">
                        <div class="temp2"></div>
                    </div>
                    <div class="col-sm-12"><hr /></div>
                    <div class="col-sm-4">
                        <div class="hum1"></div>
                    </div>
                    <div class="col-sm-8">
                        <div class="hum2"></div>
                    </div>
                </div>
            </div>
            <div class="weight">
                <div class="row weight">
                    <h4 class="title"></h4>
                    <div class="col-sm-12">
                        <div class="weight1"></div>
                    </div>
                </div>
            </div>
            <div class="flux">
                <div class="row flux">
                    <h4 class="title"></h4>
                    <div class="col-sm-12">
                        <div class="flux1"></div>
                    </div>
                </div>
            </div>
    	</div>	

	</body>
</html>
