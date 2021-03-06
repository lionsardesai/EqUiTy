function init(lines){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
//	context.fillStyle = "rgb(0,0,255)";
	//context.fillRect(30,30,50,50);
	context.strokeStyle = "rgb(255,0,0)";
//	context.strokeRect(0,0,780,550);
	context.lineWidth = 1;
	context.beginPath();
//	context.moveTo(lines[0],lines[1]);
	//context.lineTo(lines[2],lines[3]);
	//context.bezierCurveTo(50,40,50,40,150,150);
//	context.lineTo(70,30);
//	context.fill();
	context.stroke();
	context.endPath();
};

function drawVolume(data, scale, context) {
//	var canvas = document.getElementById("canvasfirst");
	//var context = canvas.getContext("2d");
	
	context.moveTo(-0.5,0.5);
	context.strokeStyle = "rgba(0,0,255,1)";
	context.lineWidth = 2.0;
	context.lineCap = "round";
	//context.stroke();
	context.beginPath();
	context.closePath();
	for(var i=0;i<data.length;i++){
		context.moveTo(i*(600/scale),300);
		context.lineTo(i*(600/scale),300-data[i]);
		context.stroke();
	}
	//context.stroke();
}

function alternategraph(data, scale){
	var canvas = document.getElementById("canvassecond");
	var context = canvas.getContext("2d");
	context.strokeStyle = "rgba(92,156,204,1)";
	context.lineWidth = 1.0;
	context.moveTo(10,10);
	context.beginPath();
	for(var i=0;i<data.length;i++) {
		context.lineTo((600/scale)*i,25-data[i]*10);
	}
	context.moveTo(0,25);
	context.lineTo(600,25);
	context.moveTo(0,0);
	context.strokeRect(0,0,600,50);
	context.stroke();
	context.closePath();
	context.fillText("tech analysis",620,25);
	context.fillText("graphs",620,15);

}
function drawLine(oldx, oldy, y, scale){
	var canvas = document.getElementById("canvassecond");
	var context = canvas.getContext("2d");
	context.strokeStyle = "rgb(255,0,0)";
	context.lineWidth = 1.0;
	context.beginPath();
	context.moveTo((780/scale)*oldx,(550-oldy));
	context.lineTo((780/scale)*(oldx+1),(550-y));
	context.stroke();
	context.closePath();
}
function drawLine2(listclose, volume, scale){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
	
	// attempts to correct line width probs
	context.translate(context.canvas.width/2, context.canvas.height/2);
	context.scale(1, 1);
	context.translate(-context.canvas.width/2, -context.canvas.height/2);
	
	context.strokeStyle = "rgba(100,156,255,1)";
	context.lineWidth = 1.0;
	context.moveTo(-0.5,0.5);
	
	// fill extremes
	context.fillText("lowest close", 620, 275);
	context.fillText("highest close", 620, 25);
	
	//cross-hairs
	context.moveTo(100,0);
	context.lineTo(100,300);
	context.stroke();
	
	context.moveTo(200,0);
	context.lineTo(200,300);
	context.stroke();
	
	context.moveTo(300,0);
	context.lineTo(300,300);
	context.stroke();
	
	context.moveTo(400,0);
	context.lineTo(400,300);
	context.stroke();
	
	context.moveTo(500,0);
	context.lineTo(500,300);
	context.stroke();
	
	context.moveTo(0,50);
	context.lineTo(600,50);
	context.stroke();
	
	context.moveTo(0,100);
	context.lineTo(600,100);
	context.stroke();
	
	context.moveTo(0,150);
	context.lineTo(600,150);
	context.stroke();
	
	context.moveTo(0,200);
	context.lineTo(600,200);
	context.stroke();
	
	context.moveTo(0,250);
	context.lineTo(600,250);
	context.stroke();

	context.lineWidth = 1;
	context.moveTo(0,300);
	context.beginPath();
	for(var i=0;i<listclose.length;i++) {
		context.lineTo((600/scale)*i,300-listclose[i]);
	}
	context.lineTo(600,300-listclose[listclose.length-1]);
	context.lineTo(600,300);
	context.lineTo(0,300);
	context.stroke();
	context.fillStyle = "rgba(92,156,204,0.7)";
	context.fill();
	context.closePath();
	drawVolume(volume, scale, context);
		
}
function drawCandle(high, low, open, close, scale){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
	
	context.strokeStyle = "rgb(255,0,0)";
	context.lineWidth = 1;
	//context.lineWidth=1*(600/scale);
	context.moveTo(-0.5,0.5);
	
	for(var i=0;i<close.length;i++) {
		context.moveTo((600/scale)*(i+0.5),300-close[i]);
		context.lineTo((600/scale)*(i+0.5),300-open[i]);
		context.stroke();
		//context.fillRect((600/scale)*i,300-close[i],(600/scale)*(i+1),300-open[i]);
	}
	
	context.strokeStyle = "rgb(92,156,204,0.7)";
	//context.fillStyle = "rgba(92,156,204,0.7)";
	//context.lineWidth = 1;
	
	for(var i=0;i<close.length;i++) {
		context.moveTo((600/scale)*(i+0.5),300-high[i]);
		context.lineTo((600/scale)*(i+0.5),300-low[i]);
		context.stroke();
	}
	//context.stroke();
}