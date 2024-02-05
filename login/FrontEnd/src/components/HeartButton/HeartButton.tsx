import useFavorite from '@/hooks/useFavorite';
import React from 'react'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import styles from './HeartButtonForReview.module.scss';

interface HeartButtonProps {
    productId: string;
    currentUser?: boolean | null;
}

const HeartButton = ({
    productId,
    currentUser
}: HeartButtonProps) => {
    const {
        hasFavorite,
        toggleFavorite
        } = useFavorite({
        productId,
        currentUser
    })
    return (
        <div
          onClick={toggleFavorite}
          className={styles.heartButton}
        >
          <AiFillHeart
            size={24}
            className={hasFavorite ? styles.heartButton__filled : styles.heartButton__outline}
          />
    
          <AiOutlineHeart
            size={24}
            className={styles.heartButton__outline}
          />
        </div>
      );
    };

export default HeartButton