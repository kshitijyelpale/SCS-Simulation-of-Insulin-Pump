export interface BslResponse {
    userId?: string;
    timeCounter?: number;
    previousBsl?: number;
    currentBsl?: number;
    message?: string;
    injectionStarted?: boolean;
    carbohydrates?: number;
}

export class Bsl {
  constructor(
    public userId: string = '',
    public timeCounter: number = 0,
    public previousBsl: number = 0,
    public currentBsl: number = 0,
    public message: string = '',
    public injectionStarted: boolean = false,
    public carbohydrates: number = 0
  ) {}
}
