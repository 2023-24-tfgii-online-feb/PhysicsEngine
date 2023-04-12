let bodies = [];
let connectionEstablished = false;
let freeze = true;
const socket = new SockJS(`${apiUrl}/physics-engine-websocket`);
const stompClient = Stomp.over(socket);

function setupWebSocket() {
  stompClient.connect({}, () => {
    connectionEstablished = true;
    stompClient.subscribe("/topic/bodies", (message) => {
      const updatedBodies = JSON.parse(message.body);
      bodies.length = 0; // Clear the existing bodies array
      Array.prototype.push.apply(bodies, updatedBodies); // Add the new bodies to the array
    });
  });

  document
    .getElementById("getRandomBody")
    .addEventListener("click", addRandomBody);
  document
    .getElementById("toggleUpdates")
    .addEventListener("click", toggleUpdates);
}

const sketch = (p) => {
  p.setup = () => {
    p.createCanvas(800, 600);
    setupWebSocket();
  };

  p.draw = () => {
    if (!connectionEstablished) {
      return;
    }

    p.background(255);

    // Draw all received bodies
    for (const body of bodies) {
      drawBody(p, body);
    }
    requestUpdateBodies();
    console.log("Tick.");
  };
};

function drawBody(p, body) {
  const { position, radius } = body;

  // Draw the body as a circle with a border
  p.noFill();
  p.stroke(0);
  p.strokeWeight(2);
  p.ellipse(position.x, position.y, radius * 2, radius * 2);
}

function requestUpdateBodies() {
  if (!freeze) {
    stompClient.send("/app/update-bodies", {}, "");
  }
}

function addRandomBody() {
  stompClient.send("/app/random-body", {}, "Requested a random body.");
}

function toggleUpdates() {
  freeze = !freeze;
  const buttonText = freeze ? "Freezed updates" : "Updating...";
  document.getElementById("toggleUpdates").innerText = buttonText;
}

const myP5 = new p5(sketch, "canvas-container");
