"use client"
import { Song, TopSong } from '@/types'
import React from 'react'
import Image from 'next/image'
import {FiPlayCircle} from 'react-icons/fi';
import { FiThumbsDown } from "react-icons/fi";
import { FiThumbsUp } from "react-icons/fi";
import { FiMoreVertical } from "react-icons/fi";
import IconButton from './elements/IconButton'
import { useRouter } from 'next/navigation'
import usePlayerState from '@/hooks/usePlayerState';

interface SongCardRowExpandProps{
    song: Song;
}


const SongCardRowExpand: React.FC<SongCardRowExpandProps> = ({song}) => {
    const {channel, channelId } = song;
    const {push} = useRouter();
    const { addSongList } = usePlayerState();
    const onClickPlay = () =>{
        addSongList([song]);
    }
    const onClickChannel = ()=>{
        push(`/channel/${channelId}`);
    } 
  return (
    <article className='flex flex-row items-center gap-4 h-[48px] w-full
        relative group'>
        <div className='w-[48px] h-[48px] relative'>
            <Image src={song.imageSrc} alt='img' fill className='object-cover' />
            <section 
            onClick={onClickPlay}
            className='hidden group-hover:flex absolute top-0 w-[48px] h-[48px] items-center justify-center
            bg-black cursor-pointer'>
                <FiPlayCircle size={20} />
            </section>
        </div>

        <div className='flex flex-row gap-4 justify-between basis-1/3'>
            <div className='w-[100px] truncate'>
                {song.name}
            </div>
            <div onClick={onClickChannel} className='text-neutral-500 hover:underline cursor-pointer'>{channel}</div>
        </div>
        <section className='hidden group-hover:flex absolute right-0 top-0 flex-row justify-end items-center h-[48px] w-[120px] bg-[rgba(0,0,0,0.7)]'>
            <IconButton icon={<FiThumbsDown size={20} />} />
            <IconButton icon={<FiThumbsUp size={20} />} />
            <IconButton icon={<FiMoreVertical size={20} />} />
        </section>
    </article>
  )
}

export default SongCardRowExpand