import { Button, Col, Drawer, Form, Input, Row, Space } from "antd";
import { useContext, useEffect, useState } from "react";

import { URL_API } from "../services/Service";
import { fetchAddToCollections } from "../context/Actions";
import AppContext from "../context/AppContext";

const CreateTypologyForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
    }
    // eslint-disable-next-line
  }, []);

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const onSubmit = (value) => {
    const body = {
      description: value.name,
    };

    const url = `${URL_API}/typologies`;

    const request = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchAddToCollections(url, request, dispatch);
    setVisible(false);
    props.onClose(false);
  };

  return (
    <>
      <Drawer
        title="Create Typology"
        width={360}
        onClose={onClose}
        visible={visible}
        bodyStyle={{
          paddingBottom: 80,
        }}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
          </Space>
        }
      >
        <Form
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
          hideRequiredMark
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="name"
                label="New Typology"
                rules={[
                  {
                    required: true,
                    message: "Please enter new typology",
                  },

                  {
                    min: 1,
                    message: "At least two characters.",
                  },
                ]}
              >
                <Input placeholder="Please enter new typology" />
              </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item
                wrapperCol={{
                  offset: 9,
                }}
              >
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Drawer>
    </>
  );
};

export default CreateTypologyForm;
