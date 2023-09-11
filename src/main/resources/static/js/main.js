import { drawBody } from './draw.js';
import { parseJsonWithFloats } from './json-parser.js';


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
      const updatedBodies = parseJsonWithFloats(message.body);
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

    requestBodies();
    p.background(255);

    // Draw all received bodies
    for (const body of bodies) {
      drawBody(p, body);
    }

  };
};




function requestBodies() {
  if (!freezeRendering) {
    stompClient.send("/app/retrieve-bodies", {}, "");
  }
}

function addRandomBody() {
  stompClient.send("/app/random-body", {}, "Requested a random body.");
}


function toggleRendering() {
  freezeRendering = !document.getElementById("toggleRendering").checked;
}



const myP5 = new p5(sketch, "canvas-container");
