export interface BslResponse {
  userId?: string;
  timeCounter?: number;
  previousBsl?: number;
  currentBsl?: number;
  message?: string;
  injectionStarted?: boolean;
  carbohydrates?: number;
  insulinInReservoir?: number;
  glucagonInReservoir?: number;
}

export class Bsl {
  constructor(
    public userId: string = '',
    public timeCounter: number = 0,
    public previousBsl: number = 0,
    public currentBsl: number = 0,
    public message: string = '',
    public injectionStarted: boolean = false,
    public carbohydrates: number = 0,
    public insulinInReservoir: number = 0,
    public glucagonInReservoir: number = 0
  ) {}
}
