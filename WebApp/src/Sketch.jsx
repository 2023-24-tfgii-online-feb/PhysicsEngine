import React, { useRef, useEffect } from 'react';
import p5 from 'p5';




const Sketch = () => {
  const sketchRef = useRef();

  useEffect(() => {



    const sketch = (p) => {
      p.setup = () => {
        p.createCanvas(1240, 720);
        p.background(255);
      };
      
      let x = 0;
      p.draw = () => {
        p.background(255);
        p.circle(x, 670, 300);
        x = x + 1;
      };
    };

    new p5(sketch, sketchRef.current);

    // Cleanup function
    return () => {
      if (sketchRef.current && sketchRef.current.childNodes[0]) {
        sketchRef.current.removeChild(sketchRef.current.childNodes[0]);
      }
    };
  }, []);

  return <div ref={sketchRef} />;
};

export default Sketch;