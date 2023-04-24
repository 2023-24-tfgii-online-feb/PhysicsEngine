let bodies = [];
let connectionEstablished = false;
let freezeUpdates = false;
let freezeRendering = false;
const socket = new SockJS('/physics-engine-websocket');
const stompClient = Stomp.over(socket);

function setupWebSocket() {
  stompClient.connect({}, () => {
    connectionEstablished = true;
    stompClient.subscribe("/topic/bodies", (message) => {
      const updatedBodies = JSON.parse(message.body);
      console.log(updatedBodies);
      bodies.length = 0; // Clear the existing bodies array
      Array.prototype.push.apply(bodies, updatedBodies); // Add the new bodies to the array

      console.log(`<< MESSAGE\n${message.headers['destination']}\nBodies received: ${updatedBodies.length}`);
    });
  });

  document
    .getElementById("getRandomBody")
    .addEventListener("click", addRandomBody);

  document.getElementById("toggleUpdates").addEventListener("change", toggleUpdates);
  document.getElementById("toggleRendering").addEventListener("change", toggleRendering);


}

const sketch = (p) => {
  p.setup = () => {
    p.createCanvas(1240,720);
    setupWebSocket();
  };
  document.getElementById("toggleRendering").checked = !freezeRendering;
  document.getElementById("toggleUpdates").checked = !freezeUpdates;


  p.draw = () => {
    if (!connectionEstablished) {
      return;
    }

    //requestUpdate();
    requestBodies();
    p.background(255);

    // Draw all received bodies
    for (const body of bodies) {
      drawBody(p, body);
    }
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

function requestBodies() {
  if (!freezeRendering) {
    stompClient.send("/app/retrieve-bodies", {}, "");
  }
}

function addRandomBody() {
  stompClient.send("/app/random-body", {}, "Requested a random body.");
}

function requestUpdate() {
  if (!freezeUpdates) {
    stompClient.send("/app/update-bodies", {}, "tick.");
  }
}

function toggleRendering() {
  freezeRendering = !document.getElementById("toggleRendering").checked;
}

function toggleUpdates() {
  freezeUpdates = !document.getElementById("toggleUpdates").checked;
}



const myP5 = new p5(sketch, "canvas-container");
