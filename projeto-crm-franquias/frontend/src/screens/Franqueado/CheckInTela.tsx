import React from 'react';
import styles from './CheckInTela.module.css';
import { CHECKINS_FRANQUIA_A_MOCK } from '../../api/mockDados';
import ItemLista from '../../components/ItemLista';

const CheckInTela: React.FC = () => {
  const checkins = CHECKINS_FRANQUIA_A_MOCK;

  const handleCheckIn = () => {
    // Context ou API
  };

  return (
    <div className={styles.container}>
      <h1>Check-in de Clientes</h1>
      
      <div className={styles.painelCheckin}>
        <input type="text" placeholder="Buscar nome do cliente..." className={styles.busca} />
        <button onClick={handleCheckIn} className={styles.botao}>Registrar Check-in</button>
      </div>

      <div className={styles.recentes}>
        <h2>Check-ins Recentes</h2>
        <div className={styles.lista}>
          {checkins.map(checkin => (
            <ItemLista key={checkin.id}>
              <span>{checkin.nomeCliente}</span>
              <span>{checkin.data}</span>
            </ItemLista>
          ))}
        </div>
      </div>
    </div>
  );
};

export default CheckInTela;