import React from 'react';
import styles from './ItemLista.module.css';

interface ItemListaProps {
  children: React.ReactNode;
  onClick?: () => void;
}

const ItemLista: React.FC<ItemListaProps> = ({ children, onClick }) => {
  const isClickable = !!onClick;
  return (
    <div 
      className={`${styles.item} ${isClickable ? styles.clickable : ''}`} 
      onClick={onClick}
    >
      {children}
    </div>
  );
};

export default ItemLista;