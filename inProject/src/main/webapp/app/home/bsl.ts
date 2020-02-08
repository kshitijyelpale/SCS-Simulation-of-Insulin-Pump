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
  isEmailSentForInsulin?: boolean;
  isEmailSentForGlucagon?: boolean;
  alertCounterForHyperLevel?: number;
  alertCounterForHypoLevel?: number;
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
    public glucagonInReservoir: number = 0,
    public isEmailSentForInsulin: boolean = false,
    public isEmailSentForGlucagon: boolean = false,
    public alertCounterForHyperLevel: number = 0,
    public alertCounterForHypoLevel: number = 0
  ) {}
}
