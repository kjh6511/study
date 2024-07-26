'use client';
import React, { useEffect } from 'react';
import useUIState from '@/hooks/useUIState';

const HeaderBgChanger = ({ imageSrc }) => {
      const {setHeaderImageSrc} = useUIState();

      useEffect(() => {
        if (imageSrc) setHeaderImageSrc(imageSrc);
      // eslint-disable-next-line react-hooks/exhaustive-deps
      }, [imageSrc]);

  return <></>;
};

export default HeaderBgChanger;
