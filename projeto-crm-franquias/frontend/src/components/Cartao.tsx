import React from 'react';
import styles from './Cartao.module.css';

interface CartaoProps {
  titulo?: string;
  children: React.ReactNode;
}

const Cartao: React.FC<CartaoProps> = ({ titulo, children }) => {
  return (
    <div className={styles.cartao}>
      {titulo && <h3 className={styles.titulo}>{titulo}</h3>}
      <div className={styles.conteudo}>
        {children}
      </div>
    </div>
  );
};

export default Cartao;