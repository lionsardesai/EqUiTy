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
function drawLine(oldx, oldy, y, scale){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
	context.strokeStyle = "rgb(255,0,0)";
	context.lineWidth = 1;
	context.beginPath();
	context.moveTo((780/scale)*oldx,(550-oldy));
	context.lineTo((780/scale)*(oldx+1),(550-y));
	context.stroke();
	context.endPath();
}
function drawLine2(listclose, scale){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
	context.strokeStyle = "rgb(255,0,0)";
	context.lineWidth = 1;
	context.beginPath();
	context.moveTo(0,0);
	for(var i=0;i<listclose.length;i++) {
		context.lineTo((780/scale)*i,550-listclose[i]);
	}
	context.stroke();
	context.endPath();
}
function drawCandle(oldx, high, low, open, close, scale){
	var canvas = document.getElementById("canvasfirst");
	var context = canvas.getContext("2d");
	context.strokeStyle = "rgb(255,0,0)";
	context.lineWidth = 1;
	context.beginPath();
	context.moveTo((780/scale)*(oldx+0.5),550-high);
	context.lineTo((780/scale)*(oldx+0.5),550-low);
	context.endPath();
	context.fillRect(oldx,550-close,oldx+1,550-open);
	
}