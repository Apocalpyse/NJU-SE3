/**
 * Created by A on 2017/6/3.
 */
document.write("<script language=javascript src='echarts.min.js'></script>");

function test1() {
    $.ajax({

        url: "/StockHubWeb/register",
        type:"POST",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout:1000,
        async: true,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:[],


        success: function(data1){
            var data=data1.toString();
            alert(data);
            document.getElementById('test').innerHTML=data;
        }
    });
}

var req;
/*通过异步传输XMLHTTP发送参数到ajaxServlet，返回符合条件的XML文档*/
var url;
function getResult()
{
    var xmlhttp;
    var txt,x,i;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("test").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","/StockHubWeb/register",true);
    xmlhttp.send();
}

function addButton() {
    var h1="hello111";
    var h2="hello222";
    document.getElementById("area").innerHTML +=
        "</br><input name=\"按钮\" type=\"button\" onClick=\"nextFunc()\" value=\"以形成期为基准\"><\/input>" +
        "<input name=\"按钮\" type=\"button\" onClick=\"nextFunc()\" value=\"以持有期为基准\"><\/input>";
}

function nextFunc(str) {
    document.getElementById('test').innerHTML="新增按钮有监听！and str=";
}

function addChart() {
    var myChart = echarts.init(document.getElementById('chart'));
// Schema:
// date,AQIindex,PM2.5,PM10,CO,NO2,SO2
    var dataBJ = [
        [55,9,56,0.46,18,6,1],
        [25,11,21,0.65,34,9,2],
        [56,7,63,0.3,14,5,3],
        [33,7,29,0.33,16,6,4],
        [42,24,44,0.76,40,16,5],
        [82,58,90,1.77,68,33,6],
        [74,49,77,1.46,48,27,7],
        [78,55,80,1.29,59,29,8],
        [267,216,280,4.8,108,64,9],
        [185,127,216,2.52,61,27,10],
        [39,19,38,0.57,31,15,11],
        [41,11,40,0.43,21,7,12],
        [64,38,74,1.04,46,22,13],
        [108,79,120,1.7,75,41,14],
        [108,63,116,1.48,44,26,15],
        [33,6,29,0.34,13,5,16],
        [94,66,110,1.54,62,31,17],
        [186,142,192,3.88,93,79,18],
        [57,31,54,0.96,32,14,19],
        [22,8,17,0.48,23,10,20],
        [39,15,36,0.61,29,13,21],
        [94,69,114,2.08,73,39,22],
        [99,73,110,2.43,76,48,23],
        [31,12,30,0.5,32,16,24],
        [42,27,43,1,53,22,25],
        [154,117,157,3.05,92,58,26],
        [234,185,230,4.09,123,69,27],
        [160,120,186,2.77,91,50,28],
        [134,96,165,2.76,83,41,29],
        [52,24,60,1.03,50,21,30],
        [46,5,49,0.28,10,6,31]
    ];

    var dataGZ = [
        [26,37,27,1.163,27,13,1],
        [85,62,71,1.195,60,8,2],
        [78,38,74,1.363,37,7,3],
        [21,21,36,0.634,40,9,4],
        [41,42,46,0.915,81,13,5],
        [56,52,69,1.067,92,16,6],
        [64,30,28,0.924,51,2,7],
        [55,48,74,1.236,75,26,8],
        [76,85,113,1.237,114,27,9],
        [91,81,104,1.041,56,40,10],
        [84,39,60,0.964,25,11,11],
        [64,51,101,0.862,58,23,12],
        [70,69,120,1.198,65,36,13],
        [77,105,178,2.549,64,16,14],
        [109,68,87,0.996,74,29,15],
        [73,68,97,0.905,51,34,16],
        [54,27,47,0.592,53,12,17],
        [51,61,97,0.811,65,19,18],
        [91,71,121,1.374,43,18,19],
        [73,102,182,2.787,44,19,20],
        [73,50,76,0.717,31,20,21],
        [84,94,140,2.238,68,18,22],
        [93,77,104,1.165,53,7,23],
        [99,130,227,3.97,55,15,24],
        [146,84,139,1.094,40,17,25],
        [113,108,137,1.481,48,15,26],
        [81,48,62,1.619,26,3,27],
        [56,48,68,1.336,37,9,28],
        [82,92,174,3.29,0,13,29],
        [106,116,188,3.628,101,16,30],
        [118,50,0,1.383,76,11,31]
    ];

    var dataSH = [
        [91,45,125,0.82,34,23,1],
        [65,27,78,0.86,45,29,2],
        [83,60,84,1.09,73,27,3],
        [109,81,121,1.28,68,51,4],
        [106,77,114,1.07,55,51,5],
        [109,81,121,1.28,68,51,6],
        [106,77,114,1.07,55,51,7],
        [89,65,78,0.86,51,26,8],
        [53,33,47,0.64,50,17,9],
        [80,55,80,1.01,75,24,10],
        [117,81,124,1.03,45,24,11],
        [99,71,142,1.1,62,42,12],
        [95,69,130,1.28,74,50,13],
        [116,87,131,1.47,84,40,14],
        [108,80,121,1.3,85,37,15],
        [134,83,167,1.16,57,43,16],
        [79,43,107,1.05,59,37,17],
        [71,46,89,0.86,64,25,18],
        [97,71,113,1.17,88,31,19],
        [84,57,91,0.85,55,31,20],
        [87,63,101,0.9,56,41,21],
        [104,77,119,1.09,73,48,22],
        [87,62,100,1,72,28,23],
        [168,128,172,1.49,97,56,24],
        [65,45,51,0.74,39,17,25],
        [39,24,38,0.61,47,17,26],
        [39,24,39,0.59,50,19,27],
        [93,68,96,1.05,79,29,28],
        [188,143,197,1.66,99,51,29],
        [174,131,174,1.55,108,50,30],
        [187,143,201,1.39,89,53,31]
    ];

    var lineStyle = {
        normal: {
            width: 1,
            opacity: 0.5
        }
    };

    option = {
        backgroundColor: '#161627',
        title: {
            text: 'AQI - 雷达图',
            left: 'center',
            textStyle: {
                color: '#eee'
            }
        },
        legend: {
            bottom: 5,
            data: ['北京', '上海', '广州'],
            itemGap: 20,
            textStyle: {
                color: '#fff',
                fontSize: 14
            },
            selectedMode: 'single'
        },
        // visualMap: {
        //     show: true,
        //     min: 0,
        //     max: 20,
        //     dimension: 6,
        //     inRange: {
        //         colorLightness: [0.5, 0.8]
        //     }
        // },
        radar: {
            indicator: [
                {name: 'AQI', max: 300},
                {name: 'PM2.5', max: 250},
                {name: 'PM10', max: 300},
                {name: 'CO', max: 5},
                {name: 'NO2', max: 200},
                {name: 'SO2', max: 100}
            ],
            shape: 'circle',
            splitNumber: 5,
            name: {
                textStyle: {
                    color: 'rgb(238, 197, 102)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: [
                        'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                        'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                        'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                    ].reverse()
                }
            },
            splitArea: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(238, 197, 102, 0.5)'
                }
            }
        },
        series: [
            {
                name: '北京',
                type: 'radar',
                lineStyle: lineStyle,
                data: dataBJ,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#F9713C'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.1
                    }
                }
            },
            {
                name: '上海',
                type: 'radar',
                lineStyle: lineStyle,
                data: dataSH,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#B3E4A1'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.05
                    }
                }
            },
            {
                name: '广州',
                type: 'radar',
                lineStyle: lineStyle,
                data: dataGZ,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: 'rgb(238, 197, 102)'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.05
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
    // document.getElementById('chart').innerHTML=chart;

    // document.getElementById("area").innerHTML +=
    //     "</br><input name=\"按钮\" type=\"button\" onClick=\"nextFunc()\" value=\"以形成期为基准\"><\/input>" +
    //     "<input name=\"按钮\" type=\"button\" onClick=\"nextFunc()\" value=\"以持有期为基准\"><\/input>";


}

function addChart2() {
    var myChart = echarts.init(document.getElementById("chart"));
    // 指定图表的配置项和数据
    //open ,close,low,high
    // var data0=getSpecificColumn(KData,"date");
    // data0=getColumnType(data0);
    // alert(data0);
    // var data1=getSpecificColumn(KData,"open");
    // alert(data1);
    // var data2=getSpecificColumn(KData,"close");
    // alert(data2);
    // var data3=getSpecificColumn(KData,"low");
    // alert(data3);
    // var data4=getSpecificColumn(KData,"high");
    // alert(data4);
    // data0=getTwoArrayCombined(data0,data1);
    // data0=getTwoArrayCombined(data0,data2);
    // data0=getTwoArrayCombined(data0,data3);
    // data0=getTwoArrayCombined(data0,data4);
    // alert(data0);

    var data0 = splitData([
        ['2013/1/24', 2320.26,2320.26,2287.3,2362.94],
        ['2013/1/25', 2300,2291.3,2288.26,2308.38],
        ['2013/1/28', 2295.35,2346.5,2295.35,2346.92],
        ['2013/1/29', 2347.22,2358.98,2337.35,2363.8],
        ['2013/1/30', 2360.75,2382.48,2347.89,2383.76],
        ['2013/1/31', 2383.43,2385.42,2371.23,2391.82],
        ['2013/2/1', 2377.41,2419.02,2369.57,2421.15],
        ['2013/2/4', 2425.92,2428.15,2417.58,2440.38],
        ['2013/2/5', 2411,2433.13,2403.3,2437.42],
        ['2013/2/6', 2432.68,2434.48,2427.7,2441.73],
        ['2013/2/7', 2430.69,2418.53,2394.22,2433.89],
        ['2013/2/8', 2416.62,2432.4,2414.4,2443.03],
        ['2013/2/18', 2441.91,2421.56,2415.43,2444.8],
        ['2013/2/19', 2420.26,2382.91,2373.53,2427.07],
        ['2013/2/20', 2383.49,2397.18,2370.61,2397.94],
        ['2013/2/21', 2378.82,2325.95,2309.17,2378.82],
        ['2013/2/22', 2322.94,2314.16,2308.76,2330.88],
        ['2013/2/25', 2320.62,2325.82,2315.01,2338.78],
        ['2013/2/26', 2313.74,2293.34,2289.89,2340.71],
        ['2013/2/27', 2297.77,2313.22,2292.03,2324.63],
        ['2013/2/28', 2322.32,2365.59,2308.92,2366.16],
        ['2013/3/1', 2364.54,2359.51,2330.86,2369.65],
        ['2013/3/4', 2332.08,2273.4,2259.25,2333.54],
        ['2013/3/5', 2274.81,2326.31,2270.1,2328.14],
        ['2013/3/6', 2333.61,2347.18,2321.6,2351.44],
        ['2013/3/7', 2340.44,2324.29,2304.27,2352.02],
        ['2013/3/8', 2326.42,2318.61,2314.59,2333.67],
        ['2013/3/11', 2314.68,2310.59,2296.58,2320.96],
        ['2013/3/12', 2309.16,2286.6,2264.83,2333.29],
        ['2013/3/13', 2282.17,2263.97,2253.25,2286.33],
        ['2013/3/14', 2255.77,2270.28,2253.31,2276.22],
        ['2013/3/15', 2269.31,2278.4,2250,2312.08],
        ['2013/3/18', 2267.29,2240.02,2239.21,2276.05],
        ['2013/3/19', 2244.26,2257.43,2232.02,2261.31],
        ['2013/3/20', 2257.74,2317.37,2257.42,2317.86],
        ['2013/3/21', 2318.21,2324.24,2311.6,2330.81],
        ['2013/3/22', 2321.4,2328.28,2314.97,2332],
        ['2013/3/25', 2334.74,2326.72,2319.91,2344.89],
        ['2013/3/26', 2318.58,2297.67,2281.12,2319.99],
        ['2013/3/27', 2299.38,2301.26,2289,2323.48],
        ['2013/3/28', 2273.55,2236.3,2232.91,2273.55],
        ['2013/3/29', 2238.49,2236.62,2228.81,2246.87]
    ]);


    function splitData(rawData) {
        var categoryData = [];
        var values = [];
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i])
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                sum += data0.values[i - j][1];
            }
            result.push(sum / dayCount);
        }
        return result;
    }



    option = {
        title: {
            text: stockTitle,
            left: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: data0.categoryData,
            scale: true,
            boundaryGap : false,
            axisLine: {onZero: false},
            splitLine: {show: false},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax'
        },
        yAxis: {
            scale: true,
            splitArea: {
                show: true
            }
        },
        dataZoom: [
            {
                type: 'inside',
                start: 50,
                end: 100
            },
            {
                show: true,
                type: 'slider',
                y: '90%',
                start: 50,
                end: 100
            }
        ],
        series: [
            {
                name: '日K',
                type: 'candlestick',
                data: data0.values,
                markPoint: {
                    label: {
                        normal: {
                            formatter: function (param) {
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [
                        {
                            name: 'XX标点',
                            coord: ['2013/5/31', 2300],
                            value: 2300,
                            itemStyle: {
                                normal: {color: 'rgb(41,60,85)'}
                            }
                        },
                        {
                            name: 'highest value',
                            type: 'max',
                            valueDim: 'highest'
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: 'lowest'
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: 'close'
                        }
                    ],
                    tooltip: {
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],
                    data: [
                        [
                            {
                                name: 'from lowest to highest',
                                type: 'min',
                                valueDim: 'lowest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            },
                            {
                                type: 'max',
                                valueDim: 'highest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            }
                        ],
                        {
                            name: 'min line on close',
                            type: 'min',
                            valueDim: 'close'
                        },
                        {
                            name: 'max line on close',
                            type: 'max',
                            valueDim: 'close'
                        }
                    ]
                }
            },
            {
                name: 'MA5',
                type: 'line',
                data: calculateMA(5),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: calculateMA(10),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: calculateMA(20),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: calculateMA(30),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}

function addChart3() {
    document.getElementById("end").innerHTML="ok in 2017/6/11";
    var decStop=10;
    var rateLessNeg5=12;
    var rateFromNeg5ToZero=23;
    var rateFromZeroTo5=46;
    var rateMore5=23;
    var incStop=5;

    var myChart = echarts.init(document.getElementById("chart"));

    option = {
        title : {
            text: '股市总体行情饼图',
            subtext: '(单日内)',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['decStop','rateLessNeg5','rateFromNeg5ToZero','rateFromZeroTo5','rateMore5','incStop']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:decStop, name:'decStop'},
                    {value:rateLessNeg5, name:'rateLessNeg5'},
                    {value:rateFromNeg5ToZero, name:'rateFromNeg5ToZero'},
                    {value:rateFromZeroTo5, name:'rateFromZeroTo5'},
                    {value:rateMore5, name:'rateMore5'},
                    {value:incStop, name:'incStop'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getData() {
    var stockNameOrCodeStr="000001";
    var startStr="2/1/16";
    var endStr="5/30/17";

    var mothodToCall="";
    var parameterName="";
    var dataPack=[];
    var tempUrl='';
    var tempData='';
    if((stockNameOrCodeStr.charAt(0)>='0')&&(stockNameOrCodeStr.charAt(0)<='9')){//如果是code
        tempUrl="/StockHubWeb/getDataByCode?" +
            "code="+stockNameOrCodeStr+"&start="+startStr+"&end="+endStr;
    }else {
        tempUrl="/StockHubWeb/getDataByName?" +
            "name="+stockNameOrCodeStr+"&start="+startStr+"&end="+endStr;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            dataPack=eval(result);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();

    document.getElementById("test").innerHTML="datapake 0 is= "+dataPack[0].date;

}