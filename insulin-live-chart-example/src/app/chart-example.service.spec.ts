import { TestBed } from '@angular/core/testing';

import { ChartExampleService } from './chart-example.service';

describe('ChartExampleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChartExampleService = TestBed.get(ChartExampleService);
    expect(service).toBeTruthy();
  });
});
