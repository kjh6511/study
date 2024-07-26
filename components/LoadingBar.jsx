'use client';
import React from 'react';
import { BarLoader, BeatLoader } from 'react-spinners';

const LoadingBar = () => {
  return (
    <div className="w-full">
      <BarLoader color="#36d7b7" cssOverride={{ width: '100%' }} />
    </div>
  );
};

export default LoadingBar;
