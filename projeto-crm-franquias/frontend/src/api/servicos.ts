import { FRANQUIAS_MOCK, LEADS_FRANQUEADORA_MOCK } from './mockDados';

const simularApi = <T>(dados: T): Promise<T> => {
  return new Promise(resolve => setTimeout(() => resolve(dados), 500));
};

export const getFranquias = () => simularApi(FRANQUIAS_MOCK);
export const getLeadsFranqueadora = () => simularApi(LEADS_FRANQUEADORA_MOCK);