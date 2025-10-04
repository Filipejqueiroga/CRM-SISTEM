import React from 'react';
import styles from './LinhaInfo.module.css';

interface LinhaInfoProps {
  label: string;
  value: string | number;
}

const LinhaInfo: React.FC<LinhaInfoProps> = ({ label, value }) => {
  return (
    <p className={styles.linha}>
      <span className={styles.label}>{label}:</span>
      <span className={styles.value}>{value}</span>
    </p>
  );
};

export default LinhaInfo;