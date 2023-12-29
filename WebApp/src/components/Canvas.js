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
        };
  
        new p5(sketch, canvasRef.current);
      }
    }, [stompClient]);
  
    return <div ref={canvasRef}></div>;
  };

  function requestBodies(stompClient) {
    stompClient.send("/app/retrieve-bodies", {}, "");
}

export default Canvas;