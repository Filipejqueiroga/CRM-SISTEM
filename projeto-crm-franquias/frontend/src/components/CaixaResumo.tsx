import React from 'react';
import styles from './CaixaResumo.module.css';
import LinhaInfo from './LinhaInfo';

interface Info {
  label: string;
  value: string | number;
}

interface CaixaResumoProps {
  titulo: string;
  infos: Info[];
}

const CaixaResumo: React.FC<CaixaResumoProps> = ({ titulo, infos }) => {
  return (
    <div className={styles.caixa}>
      <h2 className={styles.titulo}>{titulo}</h2>
      {infos.map((info, index) => (
        <LinhaInfo key={index} label={info.label} value={info.value} />
      ))}
    </div>
  );
};

export default CaixaResumo;