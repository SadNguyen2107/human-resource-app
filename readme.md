Phần mềm quản lý nhân sự sử dụng giao diện JavaFX
Maven Project

1. Quản lý nhân viên
    - Có thể tạo, sửa, xoá nhân viên
    - Các trường dữ liệu có dấu sao bên cạnh là bắt buộc phải nhập
    - Trường ngày sinh có thể chọn hoặc tự điền vào, tuy nhiên nếu tự điền cần lưu ý hình thức điền là "ngày-tháng-năm" hoặc "ngày tháng năm" hoặc "ngày/tháng/năm"
    - Thông tin nhân viên được liệt kê dưới dạng bảng sử dụng TableView
    - Có thể thêm ảnh cho nhân viên, ảnh đã thêm sẽ được lưu vào cơ sở dữ liệu
    - Có thể lọc các nhân viên với hệ số lương lớn hơn một số được nhập vào từ bàn phím
    - Chức vụ tương ứng với các chức vụ ở bảng chức vụ

2. Quản lý chức vụ
    - Có thể tạo, sửa, xoá chức vụ
    - Chức vụ có 2 trường thông tin đều bắt buộc
    - Việc sửa chức vụ sẽ thay đổi thông tin của nhân viên mang chức vụ tương ứng
    - Việc xoá chức vụ sẽ làm nhân viên có chức vụ đó mất chức vụ (Chức vụ sẽ bằng null)

3. Thông tin nhân viên và chức vụ được lưu dưới dạng file nhị phân, có thể lưu được ảnh đã thêm vào
    - Nếu đọc file bị lỗi, có thể do không có file hoặc file trống

4. Có thể chạy ngay trường trình bằng cách chạy file release.jar hoặc dùng file run.bat (Tất nhiên yêu cầu cần có JDK)

- Đăng nhập với username "admin", password "admin"