import React, { useEffect, useRef } from 'react';
import p5 from 'p5';
import "./Canvas.css";
import { parseJsonWithFloats } from '../lib/json-float-parser.js';
import { drawBody } from '../lib/draw.js';


const Canvas = ({ stompClient }) => {
    const canvasRef = useRef();
    const bodiesRef = useRef([]);
  
    useEffect(() => {
      if (stompClient) {
        const sketch = (p) => {
          p.setup = () => {
            p.createCanvas(1240, 720);
            stompClient.subscribe("/topic/bodies", (message) => {
                const updatedBodies = parseJsonWithFloats(message.body);
                bodiesRef.current.length = 0; // Clear the existing bodies array
                Array.prototype.push.apply(bodiesRef.current, updatedBodies);
            });
          };
  
          p.draw = () => {
            p.background(0);
            requestBodies(stompClient);
            console.log(bodiesRef.current);
            for (const body of bodiesRef.current) {
                drawBody(p, body);
              }
          };

          p.mouseClicked = () => {
            checkBodyClicked(p.mouseX, p.mouseY);
          };

        };
  
        new p5(sketch, canvasRef.current);
      }
    }, [stompClient]);

    const checkBodyClicked = (mouseX, mouseY) => {
      for (const body of bodiesRef.current) {
        if (isInsideBody(mouseX, mouseY, body)) {
          toggleBodySelection(body.id);
          break;
        }
      }
    };

  const isInsideBody = (x, y, body) => {
      // Assuming body has a bounding box (bbox) with properties center (x, y) and radius
      const bbox = body.bbox;
      const distance = Math.sqrt(Math.pow(x - bbox.center.x, 2) + Math.pow(y - bbox.center.y, 2));
      return distance <= bbox.radius;
  };


    const toggleBodySelection = (id) => {
      // Toggle the selection status of the body
      stompClient.send("/app/toggle-select-body", {}, JSON.stringify({ id }));
    };
  
    return <div ref={canvasRef}></div>;
  };

  function requestBodies(stompClient) {
    stompClient.send("/app/retrieve-bodies", {}, "");
}

export default Canvas;