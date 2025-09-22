export interface SMenu {
  id?: string;
  titre?: string;
  icon?: string;
  url?: string;
  active?: boolean;
  expanded?: boolean;
  sousMenu?: Array<SMenu>;
}
