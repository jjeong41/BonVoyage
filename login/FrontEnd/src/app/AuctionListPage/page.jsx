'use client'
import React, { useEffect, useState } from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios'
import getCurrentUser from '@/app/actions/getCurrentUser';
import AuctionCard from '@/components/Auctions/AuctionCard/AuctionCard'
import EmptyState from "@/components/EmptyState/EmptyState";
import m1 from './m1.jpg'
import m2 from './m2.jpg'

const AuctionListPage = async () => {

  const currentUser = '현재유저';

  const auctions = [{
    auctionSeq:1,
      imageSrc: m1,
      title: '별 헤는 밤',
  },
  {
    auctionSeq:2,
      imageSrc: m2,
      title: '샤라랄랄라랄라',
  }]

  async function handleFunc (seq, user) {

    const send = {seq:seq, userId:user};

    const data = await axios.post('https://i10a207.p.ssafy.io/api/auction', send, { headers: 
    {
      'Content-Type': 'application/json;charset=UTF-8',
    }})
    console.log('퍄퍄');
  }

  // const [auctions, setAuctions] = useState([]);

  // useEffect(() => {    
  //   const fetchData = async () => {
  //     try {
  //       const response = await axios.get('https://i10a207.p.ssafy.io/api/auction');

  //       setAuctions(response.data);
  //     } catch (error) {
  //       console.error('Error fetching products:', error);
  //       throw error; // Rethrow the error to be handled elsewhere
  //     }
  //   };
  
  //   fetchData();
  // }, [])

    return (
        <div style={{ width: '1200px' }}>
            <div style={{ marginTop: '10vh' }}>
            <h1 style={{ color: '#f1efee'}}>경매 목록</h1>
            </div>

            {
              (auctions.length === 0 || !auctions)
              ?
              <EmptyState showReset />
              :
              <>
              <div className={styles.grid}>
              {auctions.map((auction) =>
              <div onClick={() => handleFunc(auction.auctionSeq, currentUser)}>
                  <AuctionCard
                    currentUser={currentUser}
                    key={auction.auctionSeq}
                    data={auction}
                  />
                  </div>
                  )}
                  
              </div>
              </>
            }
        
            <FloatingButton
      href="/AuctionUploadPage">
        +
      </FloatingButton>

        </div>
    )
}

export default AuctionListPage