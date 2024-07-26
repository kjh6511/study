import { create } from 'zustand';

const useUIState = create((set) => ({
  homeCategory: '',
  headerImageSrc:
    'https://images.unsplash.com/photo-1511735111819-9a3f7709049c?q=80&w=1548&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  setHomeCategory: (value) => set({ homeCategory: value }),
  setHeaderImageSrc: (src) => set({ headerImageSrc: src }),
}));

export default useUIState;
