'use client';
import React from 'react';
import Logo from '@/components/elements/Logo';
import Navigator from '@/components/elements/Navigator';
import usePlayerState from '@/hooks/usePlayerState';
import {cn} from '@/lib/utils';

const Sidebar = ({ children }) => {
  const {isVisiblePlayer} = usePlayerState();
  return (
    <div className = {cn("flex flex-row h-full",
      isVisiblePlayer && "h-[calc(100ch-72px)]"
    )}>
      <nav className="hidden lg:block w-[240px] border-r-[1px] border-neutral-600">
        <div className="p-[24px]">
          <Logo />
        </div>
        <div>
          <Navigator />
        </div>
      </nav>
      <div className="w-full lg:w-[calc(100%-240px)]">{children}</div>
    </div>
  );
};

export default Sidebar;
