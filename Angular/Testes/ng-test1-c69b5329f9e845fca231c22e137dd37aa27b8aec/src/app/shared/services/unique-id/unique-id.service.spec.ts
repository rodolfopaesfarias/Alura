import { UniqueIdService } from './unique-id.service';

describe(UniqueIdService.name, () => {
  let service: UniqueIdService = null;
  beforeEach(() => {
    service = new UniqueIdService();
  });

  it(`#${UniqueIdService.prototype.generateUniqueIdWithPrefix.name}
    should generate id when called with prefix`, () => {
    const id = service.generateUniqueIdWithPrefix('app');
    expect(id.startsWith('app-')).toBeTrue();
  });

  it(`#${UniqueIdService.prototype.generateUniqueIdWithPrefix.name}
    should not generate duplicated id when called many times`, () => {
    const ids = new Set();
    for (let i = 0; i < 50; i++) {
      ids.add(service.generateUniqueIdWithPrefix('app'));
    }
    expect(ids.size).toBe(50);
  });

  it(`#${UniqueIdService.prototype.getNumberOfGeneratedUniqueIds.name}
    should return the number of generated ids when called`, () => {
    const ids = new Set();
    for (let i = 0; i < 50; i++) {
      ids.add(service.generateUniqueIdWithPrefix('app'));
    }
    expect(service.getNumberOfGeneratedUniqueIds()).toBe(50);
  });

  it(`#${UniqueIdService.prototype.getNumberOfGeneratedUniqueIds.name}
  should throw an exception when called with empty prefix`, () => {
    const emptyValues = [null, undefined, '', '0', '1'];
    emptyValues.forEach((emptyValue) => {
      expect(() => service.generateUniqueIdWithPrefix(emptyValue))
        .withContext(`Empty value ${emptyValue}`)
        .toThrow();
    });
  });
});
