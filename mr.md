/*
 * @case: 519481
 * @group: auth serverSide
 * @device: iPhone 8 Plus
 */
class TwoFactorAuthAndSmsTestCase: MRTestCase {

    ...

    // @name: testErrorCodeIn2fa
    func testErrorCodeIn2fa() throws {
        ...
    }

    /*
     * @group: auth realInBranches push serverSide
     * @device: iPhone 11|iPad Air (3rd generation)
     * @ios: 15
     * @name: testChangePasswordFor2Fa
     */
    func testChangePasswordFor2Fa() throws {
    ...
}

Атрибуты описываются в комента к тест кейсу и опционально к методам
Атрибуты метода наследуются (или перезаписываются) от атрибутов класс
Наш CI поддерживает запуск тестов по именам (атрибут @name, у нас он обязателен, но можно генерить) и по группам (@group, список групп через пробел)
Некоторые атрибуты имеют значения по умолчанию (@device: iPhone 8 Plus, @ios: 16)

В примере выше:
первый тест едет на 16 оси и iPhone 8 Plus
второй на 15 оси и iPhone 11 и iPad Air (3rd generation)